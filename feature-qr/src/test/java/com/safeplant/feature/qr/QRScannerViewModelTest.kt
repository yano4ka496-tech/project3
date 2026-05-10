package com.safeplant.feature.qr

import com.safeplant.core.database.dao.QrCodeDao
import com.safeplant.core.database.entity.QrCodeMapping
import com.safeplant.feature.map.PositionViewModel
import com.safeplant.feature.profile.ProfileViewModel
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class QRScannerViewModelTest {
    private lateinit var viewModel: QRScannerViewModel
    private val mockPositionViewModel = mockk<PositionViewModel>()
    private val mockQrCodeDao = mockk<QrCodeDao>()
    private val mockProfileViewModel = mockk<ProfileViewModel>()
    private val mockQRValidator = mockk<QRValidator>()

    @Before
    fun setup() {
        viewModel = QRScannerViewModel(mockPositionViewModel, mockQrCodeDao, mockProfileViewModel, mockQRValidator)
    }

    @Test
    fun `processQRCode should set error for invalid QR code`() = runTest {
        // Arrange
        every { mockQRValidator.validate(any()) } returns false
        
        // Act
        viewModel.processQRCode("invalid|qr")
        
        // Assert
        val scanResult = viewModel.scanResult.value
        assertNotNull(scanResult)
        assertEquals("Некорректный формат QR-кода", scanResult?.error)
        assertNull(scanResult?.warning)
        assertFalse(viewModel.isScanning.value)
    }

    @Test
    fun `processQRCode should set error for unknown objectId`() = runTest {
        // Arrange
        every { mockQRValidator.validate(any()) } returns true
        every { mockQRValidator.extractObjectId(any()) } returns "123"
        every { mockQrCodeDao.getByObjectId("123") } returns null
        
        // Act
        viewModel.processQRCode("123|Цех А")
        
        // Assert
        val scanResult = viewModel.scanResult.value
        assertNotNull(scanResult)
        assertEquals("Объект не найден", scanResult?.error)
        assertNull(scanResult?.warning)
        assertFalse(viewModel.isScanning.value)
    }

    @Test
    fun `processQRCode should update position and set warning for dangerous zone`() = runTest {
        // Arrange
        val mapping = QrCodeMapping(
            objectId = "123",
            name = "Цех А",
            latitude = 55.7558,
            longitude = 37.6173
        )
        every { mockQRValidator.validate(any()) } returns true
        every { mockQRValidator.extractObjectId(any()) } returns "123"
        every { mockQrCodeDao.getByObjectId("123") } returns mapping
        every { mockPositionViewModel.isPositionInDangerousZone(any(), any()) } returns true
        every { mockProfileViewModel.isAccessToDangerousZonesAllowed() } returns true
        
        // Act
        viewModel.processQRCode("123|Цех А")
        
        // Assert
        val scanResult = viewModel.scanResult.value
        assertNull(scanResult?.error)
        assertEquals("Вы находитесь в опасной зоне", scanResult?.warning)
        assertFalse(viewModel.isScanning.value)
    }

    @Test
    fun `processQRCode should update position and set warning for expired access`() = runTest {
        // Arrange
        val mapping = QrCodeMapping(
            objectId = "123",
            name = "Цех А",
            latitude = 55.7558,
            longitude = 37.6173
        )
        every { mockQRValidator.validate(any()) } returns true
        every { mockQRValidator.extractObjectId(any()) } returns "123"
        every { mockQrCodeDao.getByObjectId("123") } returns mapping
        every { mockPositionViewModel.isPositionInDangerousZone(any(), any()) } returns false
        every { mockProfileViewModel.isAccessToDangerousZonesAllowed() } returns false
        
        // Act
        viewModel.processQRCode("123|Цех А")
        
        // Assert
        val scanResult = viewModel.scanResult.value
        assertNull(scanResult?.error)
        assertEquals("Срок действия допуска истек", scanResult?.warning)
        assertFalse(viewModel.isScanning.value)
    }

    @Test
    fun `processQRCode should update position and set warning for both dangerous zone and expired access`() = runTest {
        // Arrange
        val mapping = QrCodeMapping(
            objectId = "123",
            name = "Цех А",
            latitude = 55.7558,
            longitude = 37.6173
        )
        every { mockQRValidator.validate(any()) } returns true
        every { mockQRValidator.extractObjectId(any()) } returns "123"
        every { mockQrCodeDao.getByObjectId("123") } returns mapping
        every { mockPositionViewModel.isPositionInDangerousZone(any(), any()) } returns true
        every { mockProfileViewModel.isAccessToDangerousZonesAllowed() } returns false
        
        // Act
        viewModel.processQRCode("123|Цех А")
        
        // Assert
        val scanResult = viewModel.scanResult.value
        assertNull(scanResult?.error)
        assertEquals("Вы находитесь в опасной зоне и срок действия допуска истек", scanResult?.warning)
        assertFalse(viewModel.isScanning.value)
    }

    @Test
    fun `processQRCode should update position without warnings`() = runTest {
        // Arrange
        val mapping = QrCodeMapping(
            objectId = "123",
            name = "Цех А",
            latitude = 55.7558,
            longitude = 37.6173
        )
        every { mockQRValidator.validate(any()) } returns true
        every { mockQRValidator.extractObjectId(any()) } returns "123"
        every { mockQrCodeDao.getByObjectId("123") } returns mapping
        every { mockPositionViewModel.isPositionInDangerousZone(any(), any()) } returns false
        every { mockProfileViewModel.isAccessToDangerousZonesAllowed() } returns true
        
        // Act
        viewModel.processQRCode("123|Цех А")
        
        // Assert
        val scanResult = viewModel.scanResult.value
        assertNull(scanResult?.error)
        assertNull(scanResult?.warning)
        assertFalse(viewModel.isScanning.value)
    }
}