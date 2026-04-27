# Fix Log: 02-01::project-initialization

---

## 2026-04-24 01:22

**User:** ── Structural Checks ──
  ✗ Test files present  (No test files found)
  ✓ Specification filled  (2393 chars)
  ✓ All stages implemented  (5/5 stages)

── Verify Commands ──

$ ./gradlew build
Starting a Gradle Daemon, 8 busy and 1 stopped Daemons could not be reused, use --status for details
> Task :app:preBuild UP-TO-DATE
> Task :app:preDebugBuild UP-TO-DATE
> Task :app:mergeDebugNativeDebugMetadata NO-SOURCE
> Task :app:checkKotlinGradlePluginConfigurationErrors SKIPPED
> Task :core-database:preBuild UP-TO-DATE
> Task :core-database:preDebugBuild UP-TO-DATE
> Task :core-database:writeDebugAarMetadata UP-TO-DATE
> Task :core-mapping:preBuild UP-TO-DATE
> Task :core-mapping:preDebugBuild UP-TO-DATE
> Task :core-mapping:writeDebugAarMetadata UP-TO-DATE
> Task :core-navigation:preBuild UP-TO-DATE
> Task :core-navigation:preDebugBuild UP-TO-DATE
> Task :core-navigation:writeDebugAarMetadata UP-TO-DATE
> Task :core-security:preBuild UP-TO-DATE
> Task :core-security:preDebugBuild UP-TO-DATE
> Task :core-security:writeDebugAarMetadata UP-TO-DATE
> Task :core-storage:preBuild UP-TO-DATE
> Task :core-storage:preDebugBuild UP-TO-DATE
> Task :core-storage:writeDebugAarMetadata UP-TO-DATE
> Task :feature-map:preBuild UP-TO-DATE
> Task :feature-map:preDebugBuild UP-TO-DATE
> Task :feature-map:writeDebugAarMetadata UP-TO-DATE
> Task :feature-profile:preBuild UP-TO-DATE
> Task :feature-profile:preDebugBuild UP-TO-DATE
> Task :feature-profile:writeDebugAarMetadata UP-TO-DATE
> Task :feature-qr:preBuild UP-TO-DATE
> Task :feature-qr:preDebugBuild UP-TO-DATE
> Task :feature-qr:writeDebugAarMetadata UP-TO-DATE
> Task :feature-quiz:preBuild UP-TO-DATE
> Task :feature-quiz:preDebugBuild UP-TO-DATE
> Task :feature-quiz:writeDebugAarMetadata UP-TO-DATE
> Task :feature-training:preBuild UP-TO-DATE
> Task :feature-training:preDebugBuild UP-TO-DATE
> Task :feature-training:writeDebugAarMetadata UP-TO-DATE
> Task :app:checkDebugAarMetadata UP-TO-DATE
> Task :app:generateDebugResValues UP-TO-DATE
> Task :core-database:generateDebugResValues UP-TO-DATE
> Task :core-database:generateDebugResources UP-TO-DATE
> Task :core-database:packageDebugResources UP-TO-DATE
> Task :core-mapping:generateDebugResValues UP-TO-DATE
> Task :core-mapping:generateDebugResources UP-TO-DATE
> Task :core-mapping:packageDebugResources UP-TO-DATE
> Task :core-navigation:generateDebugResValues UP-TO-DATE
> Task :core-navigation:generateDebugResources UP-TO-DATE
> Task :core-navigation:packageDebugResources UP-TO-DATE
> Task :core-security:generateDebugResValues UP-TO-DATE
> Task :core-security:generateDebugResources UP-TO-DATE
> Task :core-security:packageDebugResources UP-TO-DATE
> Task :core-storage:generateDebugResValues UP-TO-DATE
> Task :core-storage:generateDebugResources UP-TO-DATE
> Task :core-storage:packageDebugResources UP-TO-DATE
> Task :feature-map:generateDebugResValues UP-TO-DATE
> Task :feature-map:generateDebugResources UP-TO-DATE
> Task :feature-map:packageDebugResources UP-TO-DATE
> Task :feature-profile:generateDebugResValues UP-TO-DATE
> Task :feature-profile:generateDebugResources UP-TO-DATE
> Task :feature-profile:packageDebugResources UP-TO-DATE
> Task :feature-qr:generateDebugResValues UP-TO-DATE
> Task :feature-qr:generateDebugResources UP-TO-DATE
> Task :feature-qr:packageDebugResources UP-TO-DATE
> Task :feature-quiz:generateDebugResValues UP-TO-DATE
> Task :feature-quiz:generateDebugResources UP-TO-DATE
> Task :feature-quiz:packageDebugResources UP-TO-DATE
> Task :feature-training:generateDebugResValues UP-TO-DATE
> Task :feature-training:generateDebugResources UP-TO-DATE
> Task :feature-training:packageDebugResources UP-TO-DATE
> Task :app:mapDebugSourceSetPaths UP-TO-DATE
> Task :app:generateDebugResources UP-TO-DATE
> Task :app:mergeDebugResources UP-TO-DATE
> Task :app:packageDebugResources UP-TO-DATE
> Task :app:parseDebugLocalResources UP-TO-DATE
> Task :app:createDebugCompatibleScreenManifests UP-TO-DATE
> Task :app:extractDeepLinksDebug UP-TO-DATE
> Task :core-database:extractDeepLinksDebug UP-TO-DATE
> Task :core-database:processDebugManifest UP-TO-DATE
> Task :core-mapping:extractDeepLinksDebug UP-TO-DATE
> Task :core-mapping:processDebugManifest UP-TO-DATE
> Task :core-navigation:extractDeepLinksDebug UP-TO-DATE
> Task :core-navigation:processDebugManifest UP-TO-DATE
> Task :core-security:extractDeepLinksDebug UP-TO-DATE
> Task :core-security:processDebugManifest UP-TO-DATE
> Task :core-storage:extractDeepLinksDebug UP-TO-DATE
> Task :core-storage:processDebugManifest UP-TO-DATE
> Task :feature-map:extractDeepLinksDebug UP-TO-DATE
> Task :feature-map:processDebugManifest UP-TO-DATE
> Task :feature-profile:extractDeepLinksDebug UP-TO-DATE
> Task :feature-profile:processDebugManifest UP-TO-DATE
> Task :feature-qr:extractDeepLinksDebug UP-TO-DATE
> Task :feature-qr:processDebugManifest UP-TO-DATE
> Task :feature-quiz:extractDeepLinksDebug UP-TO-DATE
> Task :feature-quiz:processDebugManifest UP-TO-DATE
> Task :feature-training:extractDeepLinksDebug UP-TO-DATE
> Task :feature-training:processDebugManifest UP-TO-DATE
> Task :app:processDebugMainManifest UP-TO-DATE
> Task :app:processDebugManifest UP-TO-DATE
> Task :app:processDebugManifestForPackage UP-TO-DATE
> Task :core-database:compileDebugLibraryResources UP-TO-DATE
> Task :core-database:parseDebugLocalResources UP-TO-DATE
> Task :core-database:generateDebugRFile UP-TO-DATE
> Task :core-mapping:compileDebugLibraryResources UP-TO-DATE
> Task :core-mapping:parseDebugLocalResources UP-TO-DATE
> Task :core-mapping:generateDebugRFile UP-TO-DATE
> Task :core-navigation:compileDebugLibraryResources UP-TO-DATE
> Task :core-navigation:parseDebugLocalResources UP-TO-DATE
> Task :core-navigation:generateDebugRFile UP-TO-DATE
> Task :core-security:compileDebugLibraryResources UP-TO-DATE
> Task :core-security:parseDebugLocalResources UP-TO-DATE
> Task :core-security:generateDebugRFile UP-TO-DATE
> Task :core-storage:compileDebugLibraryResources UP-TO-DATE
> Task :core-storage:parseDebugLocalResources UP-TO-DATE
> Task :core-storage:generateDebugRFile UP-TO-DATE
> Task :feature-map:compileDebugLibraryResources UP-TO-DATE
> Task :feature-map:parseDebugLocalResources UP-TO-DATE
> Task :feature-map:generateDebugRFile UP-TO-DATE
> Task :feature-profile:compileDebugLibraryResources UP-TO-DATE
> Task :feature-profile:parseDebugLocalResources UP-TO-DATE
> Task :feature-profile:generateDebugRFile UP-TO-DATE
> Task :feature-qr:compileDebugLibraryResources UP-TO-DATE
> Task :feature-qr:parseDebugLocalResources UP-TO-DATE
> Task :feature-qr:generateDebugRFile UP-TO-DATE
> Task :feature-quiz:compileDebugLibraryResources UP-TO-DATE
> Task :feature-quiz:parseDebugLocalResources UP-TO-DATE
> Task :feature-quiz:generateDebugRFile UP-TO-DATE
> Task :feature-training:compileDebugLibraryResources UP-TO-DATE
> Task :feature-training:parseDebugLocalResources UP-TO-DATE
> Task :feature-training:generateDebugRFile UP-TO-DATE
> Task :app:processDebugResources UP-TO-DATE
> Task :core-database:checkKotlinGradlePluginConfigurationErrors SKIPPED
> Task :core-database:kspDebugKotlin UP-TO-DATE
> Task :core-database:kaptGenerateStubsDebugKotlin UP-TO-DATE
> Task :core-database:kaptDebugKotlin UP-TO-DATE
> Task :core-database:compileDebugKotlin UP-TO-DATE
> Task :core-database:javaPreCompileDebug UP-TO-DATE
> Task :core-database:compileDebugJavaWithJavac UP-TO-DATE
> Task :core-database:transformDebugClassesWithAsm UP-TO-DATE
> Task :core-database:bundleLibCompileToJarDebug UP-TO-DATE
> Task :core-mapping:checkKotlinGradlePluginConfigurationErrors SKIPPED
> Task :core-mapping:kaptGenerateStubsDebugKotlin UP-TO-DATE
> Task :core-mapping:kaptDebugKotlin UP-TO-DATE
> Task :core-mapping:compileDebugKotlin NO-SOURCE
> Task :core-mapping:javaPreCompileDebug UP-TO-DATE
> Task :core-mapping:compileDebugJavaWithJavac NO-SOURCE
> Task :core-mapping:transformDebugClassesWithAsm UP-TO-DATE
> Task :core-mapping:bundleLibCompileToJarDebug UP-TO-DATE
> Task :core-navigation:checkKotlinGradlePluginConfigurationErrors SKIPPED
> Task :core-navigation:kaptGenerateStubsDebugKotlin UP-TO-DATE
> Task :core-navigation:kaptDebugKotlin UP-TO-DATE
> Task :core-navigation:compileDebugKotlin UP-TO-DATE
> Task :core-navigation:javaPreCompileDebug UP-TO-DATE
> Task :core-navigation:compileDebugJavaWithJavac NO-SOURCE
> Task :core-navigation:transformDebugClassesWithAsm UP-TO-DATE
> Task :core-navigation:bundleLibCompileToJarDebug UP-TO-DATE
> Task :core-security:checkKotlinGradlePluginConfigurationErrors SKIPPED
> Task :core-security:kaptGenerateStubsDebugKotlin UP-TO-DATE
> Task :core-security:kaptDebugKotlin UP-TO-DATE
> Task :core-security:compileDebugKotlin UP-TO-DATE
> Task :core-security:javaPreCompileDebug UP-TO-DATE
> Task :core-security:compileDebugJavaWithJavac NO-SOURCE
> Task :core-security:transformDebugClassesWithAsm UP-TO-DATE
> Task :core-security:bundleLibCompileToJarDebug UP-TO-DATE
> Task :core-storage:checkKotlinGradlePluginConfigurationErrors SKIPPED
> Task :core-storage:kaptGenerateStubsDebugKotlin UP-TO-DATE
> Task :core-storage:kaptDebugKotlin UP-TO-DATE
> Task :core-storage:compileDebugKotlin UP-TO-DATE
> Task :core-storage:javaPreCompileDebug UP-TO-DATE
> Task :core-storage:compileDebugJavaWithJavac NO-SOURCE
> Task :core-storage:transformDebugClassesWithAsm UP-TO-DATE
> Task :core-storage:bundleLibCompileToJarDebug UP-TO-DATE
> Task :core-database:bundleLibRuntimeToJarDebug UP-TO-DATE
> Task :feature-map:checkKotlinGradlePluginConfigurationErrors SKIPPED
> Task :feature-map:kaptGenerateStubsDebugKotlin UP-TO-DATE
> Task :feature-map:kaptDebugKotlin UP-TO-DATE
> Task :feature-map:compileDebugKotlin UP-TO-DATE
> Task :feature-map:javaPreCompileDebug UP-TO-DATE
> Task :feature-map:compileDebugJavaWithJavac UP-TO-DATE
> Task :feature-map:transformDebugClassesWithAsm UP-TO-DATE
> Task :feature-map:bundleLibCompileToJarDebug UP-TO-DATE
> Task :feature-profile:checkKotlinGradlePluginConfigurationErrors SKIPPED
> Task :feature-profile:kaptGenerateStubsDebugKotlin UP-TO-DATE
> Task :feature-profile:kaptDebugKotlin UP-TO-DATE
> Task :feature-profile:compileDebugKotlin UP-TO-DATE
> Task :feature-profile:javaPreCompileDebug UP-TO-DATE
> Task :feature-profile:compileDebugJavaWithJavac UP-TO-DATE
> Task :feature-profile:transformDebugClassesWithAsm UP-TO-DATE
> Task :feature-profile:bundleLibCompileToJarDebug UP-TO-DATE
> Task :feature-qr:checkKotlinGradlePluginConfigurationErrors SKIPPED
> Task :feature-qr:kaptGenerateStubsDebugKotlin UP-TO-DATE
> Task :feature-qr:kaptDebugKotlin UP-TO-DATE
> Task :feature-qr:compileDebugKotlin UP-TO-DATE
> Task :feature-qr:javaPreCompileDebug UP-TO-DATE
> Task :feature-qr:compileDebugJavaWithJavac UP-TO-DATE
> Task :feature-qr:transformDebugClassesWithAsm UP-TO-DATE
> Task :feature-qr:bundleLibCompileToJarDebug UP-TO-DATE
> Task :feature-quiz:checkKotlinGradlePluginConfigurationErrors SKIPPED
> Task :feature-quiz:kaptGenerateStubsDebugKotlin UP-TO-DATE
> Task :feature-quiz:kaptDebugKotlin UP-TO-DATE
> Task :feature-quiz:compileDebugKotlin UP-TO-DATE
> Task :feature-quiz:javaPreCompileDebug UP-TO-DATE
> Task :feature-quiz:compileDebugJavaWithJavac UP-TO-DATE
> Task :feature-quiz:transformDebugClassesWithAsm UP-TO-DATE
> Task :feature-quiz:bundleLibCompileToJarDebug UP-TO-DATE
> Task :feature-training:checkKotlinGradlePluginConfigurationErrors SKIPPED
> Task :feature-training:kaptGenerateStubsDebugKotlin UP-TO-DATE
> Task :feature-training:kaptDebugKotlin UP-TO-DATE
> Task :feature-training:compileDebugKotlin UP-TO-DATE
> Task :feature-training:javaPreCompileDebug UP-TO-DATE
> Task :feature-training:compileDebugJavaWithJavac UP-TO-DATE
> Task :feature-training:transformDebugClassesWithAsm UP-TO-DATE
> Task :feature-training:bundleLibCompileToJarDebug UP-TO-DATE
> Task :app:kaptGenerateStubsDebugKotlin UP-TO-DATE
> Task :app:kaptDebugKotlin UP-TO-DATE
> Task :app:compileDebugKotlin UP-TO-DATE
> Task :app:javaPreCompileDebug UP-TO-DATE
> Task :app:compileDebugJavaWithJavac UP-TO-DATE
> Task :app:mergeDebugShaders UP-TO-DATE
> Task :app:compileDebugShaders NO-SOURCE
> Task :app:generateDebugAssets UP-TO-DATE
> Task :core-database:mergeDebugShaders UP-TO-DATE
> Task :core-database:compileDebugShaders NO-SOURCE
> Task :core-database:generateDebugAssets UP-TO-DATE
> Task :core-database:packageDebugAssets UP-TO-DATE
> Task :core-mapping:mergeDebugShaders UP-TO-DATE
> Task :core-mapping:compileDebugShaders NO-SOURCE
> Task :core-mapping:generateDebugAssets UP-TO-DATE
> Task :core-mapping:packageDebugAssets UP-TO-DATE
> Task :core-navigation:mergeDebugShaders UP-TO-DATE
> Task :core-navigation:compileDebugShaders NO-SOURCE
> Task :core-navigation:generateDebugAssets UP-TO-DATE
> Task :core-navigation:packageDebugAssets UP-TO-DATE
> Task :core-security:mergeDebugShaders UP-TO-DATE
> Task :core-security:compileDebugShaders NO-SOURCE
> Task :core-security:generateDebugAssets UP-TO-DATE
> Task :core-security:packageDebugAssets UP-TO-DATE
> Task :core-storage:mergeDebugShaders UP-TO-DATE
> Task :core-storage:compileDebugShaders NO-SOURCE
> Task :core-storage:generateDebugAssets UP-TO-DATE
> Task :core-storage:packageDebugAssets UP-TO-DATE
> Task :feature-map:mergeDebugShaders UP-TO-DATE
> Task :feature-map:compileDebugShaders NO-SOURCE
> Task :feature-map:generateDebugAssets UP-TO-DATE
> Task :feature-map:packageDebugAssets UP-TO-DATE
> Task :feature-profile:mergeDebugShaders UP-TO-DATE
> Task :feature-profile:compileDebugShaders NO-SOURCE
> Task :feature-profile:generateDebugAssets UP-TO-DATE
> Task :feature-profile:packageDebugAssets UP-TO-DATE
> Task :feature-qr:mergeDebugShaders UP-TO-DATE
> Task :feature-qr:compileDebugShaders NO-SOURCE
> Task :feature-qr:generateDebugAssets UP-TO-DATE
> Task :feature-qr:packageDebugAssets UP-TO-DATE
> Task :feature-quiz:mergeDebugShaders UP-TO-DATE
> Task :feature-quiz:compileDebugShaders NO-SOURCE
> Task :feature-quiz:generateDebugAssets UP-TO-DATE
> Task :feature-quiz:packageDebugAssets UP-TO-DATE
> Task :feature-training:mergeDebugShaders UP-TO-DATE
> Task :feature-training:compileDebugShaders NO-SOURCE
> Task :feature-training:generateDebugAssets UP-TO-DATE
> Task :feature-training:packageDebugAssets UP-TO-DATE
> Task :app:mergeDebugAssets UP-TO-DATE
> Task :app:compressDebugAssets UP-TO-DATE
> Task :core-navigation:bundleLibRuntimeToJarDebug UP-TO-DATE
> Task :feature-quiz:bundleLibRuntimeToJarDebug UP-TO-DATE
> Task :feature-map:bundleLibRuntimeToJarDebug UP-TO-DATE
> Task :feature-training:bundleLibRuntimeToJarDebug UP-TO-DATE
> Task :feature-qr:bundleLibRuntimeToJarDebug UP-TO-DATE
> Task :feature-profile:bundleLibRuntimeToJarDebug UP-TO-DATE
> Task :core-storage:bundleLibRuntimeToJarDebug UP-TO-DATE
> Task :core-security:bundleLibRuntimeToJarDebug UP-TO-DATE
> Task :core-mapping:bundleLibRuntimeToJarDebug UP-TO-DATE
> Task :app:desugarDebugFileDependencies UP-TO-DATE
> Task :core-database:processDebugJavaRes UP-TO-DATE
> Task :app:hiltAggregateDepsDebug UP-TO-DATE

> Task :app:hiltJavaCompileDebug
Note: /home/yano4/project3/app/build/generated/hilt/component_sources/debug/com/safeplant/Hilt_SafePlantApplication.java uses or overrides a deprecated API.
Note: Recompile with -Xlint:deprecation for details.

> Task :app:processDebugJavaRes
> Task :core-mapping:processDebugJavaRes NO-SOURCE
> Task :core-navigation:processDebugJavaRes UP-TO-DATE
> Task :core-security:processDebugJavaRes UP-TO-DATE
> Task :core-storage:processDebugJavaRes UP-TO-DATE
> Task :feature-map:processDebugJavaRes UP-TO-DATE
> Task :feature-profile:processDebugJavaRes UP-TO-DATE
> Task :feature-qr:processDebugJavaRes UP-TO-DATE
> Task :feature-quiz:processDebugJavaRes UP-TO-DATE
> Task :feature-training:processDebugJavaRes UP-TO-DATE
> Task :app:transformDebugClassesWithAsm
> Task :app:dexBuilderDebug
> Task :app:checkDebugDuplicateClasses UP-TO-DATE
> Task :app:mergeDebugGlobalSynthetics
> Task :core-navigation:bundleLibRuntimeToDirDebug
> Task :feature-map:bundleLibRuntimeToDirDebug
> Task :feature-quiz:bundleLibRuntimeToDirDebug
> Task :feature-training:bundleLibRuntimeToDirDebug
> Task :feature-qr:bundleLibRuntimeToDirDebug
> Task :feature-profile:bundleLibRuntimeToDirDebug
> Task :core-mapping:bundleLibRuntimeToDirDebug UP-TO-DATE
> Task :core-security:bundleLibRuntimeToDirDebug
> Task :core-storage:bundleLibRuntimeToDirDebug
> Task :app:mergeDebugJniLibFolders UP-TO-DATE
> Task :core-database:mergeDebugJniLibFolders UP-TO-DATE
> Task :core-database:mergeDebugNativeLibs NO-SOURCE
> Task :core-database:copyDebugJniLibsProjectOnly UP-TO-DATE
> Task :core-mapping:mergeDebugJniLibFolders UP-TO-DATE
> Task :core-mapping:mergeDebugNativeLibs NO-SOURCE
> Task :core-mapping:copyDebugJniLibsProjectOnly UP-TO-DATE
> Task :core-navigation:mergeDebugJniLibFolders UP-TO-DATE
> Task :core-navigation:mergeDebugNativeLibs NO-SOURCE
> Task :core-navigation:copyDebugJniLibsProjectOnly UP-TO-DATE
> Task :core-security:mergeDebugJniLibFolders UP-TO-DATE
> Task :core-security:mergeDebugNativeLibs NO-SOURCE
> Task :core-database:bundleLibRuntimeToDirDebug
> Task :core-security:copyDebugJniLibsProjectOnly UP-TO-DATE
> Task :core-storage:mergeDebugJniLibFolders UP-TO-DATE
> Task :core-storage:mergeDebugNativeLibs NO-SOURCE
> Task :core-storage:copyDebugJniLibsProjectOnly UP-TO-DATE
> Task :feature-map:mergeDebugJniLibFolders UP-TO-DATE
> Task :feature-map:mergeDebugNativeLibs NO-SOURCE
> Task :feature-map:copyDebugJniLibsProjectOnly UP-TO-DATE
> Task :feature-profile:mergeDebugJniLibFolders UP-TO-DATE
> Task :feature-profile:mergeDebugNativeLibs NO-SOURCE
> Task :feature-profile:copyDebugJniLibsProjectOnly UP-TO-DATE
> Task :feature-qr:mergeDebugJniLibFolders UP-TO-DATE
> Task :feature-qr:mergeDebugNativeLibs NO-SOURCE
> Task :feature-qr:copyDebugJniLibsProjectOnly UP-TO-DATE
> Task :feature-quiz:mergeDebugJniLibFolders UP-TO-DATE
> Task :feature-quiz:mergeDebugNativeLibs NO-SOURCE
> Task :feature-quiz:copyDebugJniLibsProjectOnly UP-TO-DATE
> Task :feature-training:mergeDebugJniLibFolders UP-TO-DATE
> Task :feature-training:mergeDebugNativeLibs NO-SOURCE
> Task :feature-training:copyDebugJniLibsProjectOnly UP-TO-DATE
> Task :app:mergeProjectDexDebug
> Task :app:mergeDebugNativeLibs UP-TO-DATE
> Task :app:stripDebugDebugSymbols UP-TO-DATE
> Task :app:validateSigningDebug UP-TO-DATE
> Task :app:writeDebugAppMetadata UP-TO-DATE
> Task :app:writeDebugSigningConfigVersions UP-TO-DATE
> Task :app:buildKotlinToolingMetadata UP-TO-DATE
> Task :app:preReleaseBuild UP-TO-DATE
> Task :core-database:preReleaseBuild UP-TO-DATE
> Task :core-database:writeReleaseAarMetadata UP-TO-DATE
> Task :core-mapping:preReleaseBuild UP-TO-DATE
> Task :core-mapping:writeReleaseAarMetadata UP-TO-DATE
> Task :core-navigation:preReleaseBuild UP-TO-DATE
> Task :core-navigation:writeReleaseAarMetadata UP-TO-DATE
> Task :core-security:preReleaseBuild UP-TO-DATE
> Task :core-security:writeReleaseAarMetadata UP-TO-DATE
> Task :core-storage:preReleaseBuild UP-TO-DATE
> Task :core-storage:writeReleaseAarMetadata UP-TO-DATE
> Task :feature-map:preReleaseBuild UP-TO-DATE
> Task :feature-map:writeReleaseAarMetadata UP-TO-DATE
> Task :feature-profile:preReleaseBuild UP-TO-DATE
> Task :feature-profile:writeReleaseAarMetadata UP-TO-DATE
> Task :feature-qr:preReleaseBuild UP-TO-DATE
> Task :feature-qr:writeReleaseAarMetadata UP-TO-DATE
> Task :feature-quiz:preReleaseBuild UP-TO-DATE
> Task :feature-quiz:writeReleaseAarMetadata UP-TO-DATE
> Task :feature-training:preReleaseBuild UP-TO-DATE
> Task :feature-training:writeReleaseAarMetadata UP-TO-DATE
> Task :app:mergeLibDexDebug
> Task :app:checkReleaseAarMetadata UP-TO-DATE
> Task :app:generateReleaseResValues UP-TO-DATE
> Task :core-database:generateReleaseResValues UP-TO-DATE
> Task :core-database:generateReleaseResources UP-TO-DATE
> Task :core-database:packageReleaseResources UP-TO-DATE
> Task :core-mapping:generateReleaseResValues UP-TO-DATE
> Task :core-mapping:generateReleaseResources UP-TO-DATE
> Task :core-mapping:packageReleaseResources UP-TO-DATE
> Task :core-navigation:generateReleaseResValues UP-TO-DATE
> Task :core-navigation:generateReleaseResources UP-TO-DATE
> Task :core-navigation:packageReleaseResources UP-TO-DATE
> Task :core-security:generateReleaseResValues UP-TO-DATE
> Task :core-security:generateReleaseResources UP-TO-DATE
> Task :core-security:packageReleaseResources UP-TO-DATE
> Task :core-storage:generateReleaseResValues UP-TO-DATE
> Task :core-storage:generateReleaseResources UP-TO-DATE
> Task :core-storage:packageReleaseResources UP-TO-DATE
> Task :feature-map:generateReleaseResValues UP-TO-DATE
> Task :feature-map:generateReleaseResources UP-TO-DATE
> Task :feature-map:packageReleaseResources UP-TO-DATE
> Task :feature-profile:generateReleaseResValues UP-TO-DATE
> Task :feature-profile:generateReleaseResources UP-TO-DATE
> Task :feature-profile:packageReleaseResources UP-TO-DATE
> Task :feature-qr:generateReleaseResValues UP-TO-DATE
> Task :feature-qr:generateReleaseResources UP-TO-DATE
> Task :feature-qr:packageReleaseResources UP-TO-DATE
> Task :feature-quiz:generateReleaseResValues UP-TO-DATE
> Task :feature-quiz:generateReleaseResources UP-TO-DATE
> Task :feature-quiz:packageReleaseResources UP-TO-DATE
> Task :feature-training:generateReleaseResValues UP-TO-DATE
> Task :feature-training:generateReleaseResources UP-TO-DATE
> Task :feature-training:packageReleaseResources UP-TO-DATE
> Task :app:mapReleaseSourceSetPaths UP-TO-DATE
> Task :app:generateReleaseResources UP-TO-DATE
> Task :app:mergeReleaseResources UP-TO-DATE
> Task :app:packageReleaseResources UP-TO-DATE
> Task :app:parseReleaseLocalResources UP-TO-DATE
> Task :app:createReleaseCompatibleScreenManifests UP-TO-DATE
> Task :app:extractDeepLinksRelease UP-TO-DATE
> Task :core-database:extractDeepLinksRelease UP-TO-DATE
> Task :core-database:processReleaseManifest UP-TO-DATE
> Task :core-mapping:extractDeepLinksRelease UP-TO-DATE
> Task :core-mapping:processReleaseManifest UP-TO-DATE
> Task :core-navigation:extractDeepLinksRelease UP-TO-DATE
> Task :core-navigation:processReleaseManifest UP-TO-DATE
> Task :core-security:extractDeepLinksRelease UP-TO-DATE
> Task :core-security:processReleaseManifest UP-TO-DATE
> Task :core-storage:extractDeepLinksRelease UP-TO-DATE
> Task :core-storage:processReleaseManifest UP-TO-DATE
> Task :feature-map:extractDeepLinksRelease UP-TO-DATE
> Task :feature-map:processReleaseManifest UP-TO-DATE
> Task :feature-profile:extractDeepLinksRelease UP-TO-DATE
> Task :feature-profile:processReleaseManifest UP-TO-DATE
> Task :feature-qr:extractDeepLinksRelease UP-TO-DATE
> Task :feature-qr:processReleaseManifest UP-TO-DATE
> Task :feature-quiz:extractDeepLinksRelease UP-TO-DATE
> Task :feature-quiz:processReleaseManifest UP-TO-DATE
> Task :feature-training:extractDeepLinksRelease UP-TO-DATE
> Task :feature-training:processReleaseManifest UP-TO-DATE
> Task :app:processReleaseMainManifest UP-TO-DATE
> Task :app:processReleaseManifest UP-TO-DATE
> Task :app:processReleaseManifestForPackage UP-TO-DATE
> Task :core-database:compileReleaseLibraryResources UP-TO-DATE
> Task :core-database:parseReleaseLocalResources UP-TO-DATE
> Task :core-database:generateReleaseRFile UP-TO-DATE
> Task :core-mapping:compileReleaseLibraryResources UP-TO-DATE
> Task :core-mapping:parseReleaseLocalResources UP-TO-DATE
> Task :core-mapping:generateReleaseRFile UP-TO-DATE
> Task :core-navigation:compileReleaseLibraryResources UP-TO-DATE
> Task :core-navigation:parseReleaseLocalResources UP-TO-DATE
> Task :core-navigation:generateReleaseRFile UP-TO-DATE
> Task :core-security:compileReleaseLibraryResources UP-TO-DATE
> Task :core-security:parseReleaseLocalResources UP-TO-DATE
> Task :core-security:generateReleaseRFile UP-TO-DATE
> Task :core-storage:compileReleaseLibraryResources UP-TO-DATE
> Task :core-storage:parseReleaseLocalResources UP-TO-DATE
> Task :core-storage:generateReleaseRFile UP-TO-DATE
> Task :feature-map:compileReleaseLibraryResources UP-TO-DATE
> Task :feature-map:parseReleaseLocalResources UP-TO-DATE
> Task :feature-map:generateReleaseRFile UP-TO-DATE
> Task :feature-profile:compileReleaseLibraryResources UP-TO-DATE
> Task :feature-profile:parseReleaseLocalResources UP-TO-DATE
> Task :feature-profile:generateReleaseRFile UP-TO-DATE
> Task :feature-qr:compileReleaseLibraryResources UP-TO-DATE
> Task :feature-qr:parseReleaseLocalResources UP-TO-DATE
> Task :feature-qr:generateReleaseRFile UP-TO-DATE
> Task :feature-quiz:compileReleaseLibraryResources UP-TO-DATE
> Task :feature-quiz:parseReleaseLocalResources UP-TO-DATE
> Task :feature-quiz:generateReleaseRFile UP-TO-DATE
> Task :feature-training:compileReleaseLibraryResources UP-TO-DATE
> Task :feature-training:parseReleaseLocalResources UP-TO-DATE
> Task :feature-training:generateReleaseRFile UP-TO-DATE
> Task :app:processReleaseResources UP-TO-DATE
> Task :core-database:kspReleaseKotlin UP-TO-DATE
> Task :core-database:kaptGenerateStubsReleaseKotlin UP-TO-DATE
> Task :core-database:kaptReleaseKotlin UP-TO-DATE
> Task :core-database:compileReleaseKotlin UP-TO-DATE
> Task :core-database:javaPreCompileRelease UP-TO-DATE
> Task :core-database:compileReleaseJavaWithJavac UP-TO-DATE
> Task :core-database:transformReleaseClassesWithAsm UP-TO-DATE
> Task :core-database:bundleLibCompileToJarRelease UP-TO-DATE
> Task :core-mapping:kaptGenerateStubsReleaseKotlin UP-TO-DATE
> Task :core-mapping:kaptReleaseKotlin UP-TO-DATE
> Task :core-mapping:compileReleaseKotlin NO-SOURCE
> Task :core-mapping:javaPreCompileRelease UP-TO-DATE
> Task :core-mapping:compileReleaseJavaWithJavac NO-SOURCE
> Task :core-mapping:transformReleaseClassesWithAsm UP-TO-DATE
> Task :core-mapping:bundleLibCompileToJarRelease UP-TO-DATE
> Task :core-navigation:kaptGenerateStubsReleaseKotlin UP-TO-DATE
> Task :core-navigation:kaptReleaseKotlin UP-TO-DATE
> Task :core-navigation:compileReleaseKotlin UP-TO-DATE
> Task :core-navigation:javaPreCompileRelease UP-TO-DATE
> Task :core-navigation:compileReleaseJavaWithJavac NO-SOURCE
> Task :core-navigation:transformReleaseClassesWithAsm UP-TO-DATE
> Task :core-navigation:bundleLibCompileToJarRelease UP-TO-DATE
> Task :core-security:kaptGenerateStubsReleaseKotlin UP-TO-DATE
> Task :core-security:kaptReleaseKotlin UP-TO-DATE
> Task :core-security:compileReleaseKotlin UP-TO-DATE
> Task :core-security:javaPreCompileRelease UP-TO-DATE
> Task :core-security:compileReleaseJavaWithJavac NO-SOURCE
> Task :core-security:transformReleaseClassesWithAsm UP-TO-DATE
> Task :core-security:bundleLibCompileToJarRelease UP-TO-DATE
> Task :core-storage:kaptGenerateStubsReleaseKotlin UP-TO-DATE
> Task :core-storage:kaptReleaseKotlin UP-TO-DATE
> Task :core-storage:compileReleaseKotlin UP-TO-DATE
> Task :core-storage:javaPreCompileRelease UP-TO-DATE
> Task :core-storage:compileReleaseJavaWithJavac NO-SOURCE
> Task :core-storage:transformReleaseClassesWithAsm UP-TO-DATE
> Task :core-storage:bundleLibCompileToJarRelease UP-TO-DATE
> Task :core-database:bundleLibRuntimeToJarRelease UP-TO-DATE
> Task :feature-map:kaptGenerateStubsReleaseKotlin UP-TO-DATE
> Task :feature-map:kaptReleaseKotlin UP-TO-DATE
> Task :feature-map:compileReleaseKotlin UP-TO-DATE
> Task :feature-map:javaPreCompileRelease UP-TO-DATE
> Task :app:mergeDebugJavaResource
> Task :feature-map:compileReleaseJavaWithJavac UP-TO-DATE
> Task :feature-map:transformReleaseClassesWithAsm UP-TO-DATE
> Task :feature-map:bundleLibCompileToJarRelease UP-TO-DATE
> Task :feature-profile:kaptGenerateStubsReleaseKotlin UP-TO-DATE
> Task :feature-profile:kaptReleaseKotlin UP-TO-DATE
> Task :feature-profile:compileReleaseKotlin UP-TO-DATE
> Task :feature-profile:javaPreCompileRelease UP-TO-DATE
> Task :feature-profile:compileReleaseJavaWithJavac UP-TO-DATE
> Task :feature-profile:transformReleaseClassesWithAsm UP-TO-DATE
> Task :feature-profile:bundleLibCompileToJarRelease UP-TO-DATE
> Task :feature-qr:kaptGenerateStubsReleaseKotlin UP-TO-DATE
> Task :feature-qr:kaptReleaseKotlin UP-TO-DATE
> Task :feature-qr:compileReleaseKotlin UP-TO-DATE
> Task :feature-qr:javaPreCompileRelease UP-TO-DATE
> Task :feature-qr:compileReleaseJavaWithJavac UP-TO-DATE
> Task :feature-qr:transformReleaseClassesWithAsm UP-TO-DATE
> Task :feature-qr:bundleLibCompileToJarRelease UP-TO-DATE
> Task :feature-quiz:kaptGenerateStubsReleaseKotlin UP-TO-DATE
> Task :feature-quiz:kaptReleaseKotlin UP-TO-DATE
> Task :feature-quiz:compileReleaseKotlin UP-TO-DATE
> Task :feature-quiz:javaPreCompileRelease UP-TO-DATE
> Task :feature-quiz:compileReleaseJavaWithJavac UP-TO-DATE
> Task :feature-quiz:transformReleaseClassesWithAsm UP-TO-DATE
> Task :feature-quiz:bundleLibCompileToJarRelease UP-TO-DATE
> Task :feature-training:kaptGenerateStubsReleaseKotlin UP-TO-DATE
> Task :feature-training:kaptReleaseKotlin UP-TO-DATE
> Task :feature-training:compileReleaseKotlin UP-TO-DATE
> Task :feature-training:javaPreCompileRelease UP-TO-DATE
> Task :feature-training:compileReleaseJavaWithJavac UP-TO-DATE
> Task :feature-training:transformReleaseClassesWithAsm UP-TO-DATE
> Task :feature-training:bundleLibCompileToJarRelease UP-TO-DATE
> Task :app:kaptGenerateStubsReleaseKotlin UP-TO-DATE
> Task :app:javaPreCompileRelease UP-TO-DATE
> Task :app:extractProguardFiles
> Task :core-navigation:bundleLibRuntimeToJarRelease UP-TO-DATE
> Task :feature-quiz:bundleLibRuntimeToJarRelease UP-TO-DATE
> Task :feature-map:bundleLibRuntimeToJarRelease UP-TO-DATE
> Task :feature-training:bundleLibRuntimeToJarRelease UP-TO-DATE
> Task :feature-qr:bundleLibRuntimeToJarRelease UP-TO-DATE
> Task :feature-profile:bundleLibRuntimeToJarRelease UP-TO-DATE
> Task :core-storage:bundleLibRuntimeToJarRelease UP-TO-DATE
> Task :core-security:bundleLibRuntimeToJarRelease UP-TO-DATE
> Task :core-mapping:bundleLibRuntimeToJarRelease UP-TO-DATE
> Task :core-database:processReleaseJavaRes UP-TO-DATE
> Task :core-database:createFullJarRelease
> Task :core-database:extractProguardFiles
> Task :core-database:generateReleaseLintModel
> Task :core-mapping:processReleaseJavaRes NO-SOURCE
> Task :core-database:prepareLintJarForPublish
> Task :core-mapping:createFullJarRelease
> Task :core-mapping:extractProguardFiles
> Task :core-mapping:generateReleaseLintModel
> Task :core-mapping:prepareLintJarForPublish
> Task :core-navigation:processReleaseJavaRes UP-TO-DATE
> Task :core-navigation:createFullJarRelease
> Task :core-navigation:extractProguardFiles
> Task :core-navigation:generateReleaseLintModel
> Task :core-navigation:prepareLintJarForPublish
> Task :core-security:processReleaseJavaRes UP-TO-DATE
> Task :core-security:createFullJarRelease
> Task :core-security:extractProguardFiles
> Task :core-security:generateReleaseLintModel
> Task :core-security:prepareLintJarForPublish
> Task :core-storage:processReleaseJavaRes UP-TO-DATE
> Task :core-storage:createFullJarRelease
> Task :core-storage:extractProguardFiles
> Task :core-storage:generateReleaseLintModel
> Task :core-storage:prepareLintJarForPublish
> Task :feature-map:processReleaseJavaRes UP-TO-DATE
> Task :feature-map:createFullJarRelease
> Task :feature-map:extractProguardFiles
> Task :feature-map:generateReleaseLintModel
> Task :feature-map:prepareLintJarForPublish
> Task :feature-profile:processReleaseJavaRes UP-TO-DATE
> Task :feature-profile:createFullJarRelease
> Task :feature-profile:extractProguardFiles
> Task :feature-profile:generateReleaseLintModel
> Task :feature-profile:prepareLintJarForPublish
> Task :feature-qr:processReleaseJavaRes UP-TO-DATE
> Task :feature-qr:createFullJarRelease
> Task :feature-qr:extractProguardFiles
> Task :feature-qr:generateReleaseLintModel
> Task :feature-qr:prepareLintJarForPublish
> Task :feature-quiz:processReleaseJavaRes UP-TO-DATE
> Task :feature-quiz:createFullJarRelease
> Task :feature-quiz:extractProguardFiles
> Task :feature-quiz:generateReleaseLintModel
> Task :feature-quiz:prepareLintJarForPublish
> Task :feature-training:processReleaseJavaRes UP-TO-DATE
> Task :feature-training:createFullJarRelease
> Task :feature-training:extractProguardFiles
> Task :feature-training:generateReleaseLintModel
> Task :feature-training:prepareLintJarForPublish
> Task :core-navigation:mergeReleaseJniLibFolders
> Task :core-navigation:mergeReleaseNativeLibs NO-SOURCE
> Task :core-navigation:stripReleaseDebugSymbols NO-SOURCE
> Task :core-navigation:extractDeepLinksForAarRelease
> Task :core-navigation:copyReleaseJniLibsProjectAndLocalJars
> Task :core-navigation:extractReleaseAnnotations
> Task :core-navigation:mergeReleaseGeneratedProguardFiles
> Task :core-navigation:mergeReleaseConsumerProguardFiles
> Task :core-navigation:mergeReleaseShaders
> Task :core-navigation:compileReleaseShaders NO-SOURCE
> Task :core-navigation:generateReleaseAssets UP-TO-DATE
> Task :core-navigation:packageReleaseAssets
> Task :core-navigation:prepareReleaseArtProfile
> Task :core-navigation:mergeReleaseJavaResource
> Task :feature-quiz:mergeReleaseJniLibFolders
> Task :core-navigation:syncReleaseLibJars
> Task :core-navigation:bundleReleaseLocalLintAar
> Task :feature-quiz:mergeReleaseNativeLibs NO-SOURCE
> Task :feature-quiz:stripReleaseDebugSymbols NO-SOURCE
> Task :feature-quiz:copyReleaseJniLibsProjectAndLocalJars
> Task :feature-quiz:extractDeepLinksForAarRelease
> Task :feature-quiz:extractReleaseAnnotations
> Task :feature-quiz:mergeReleaseGeneratedProguardFiles
> Task :feature-quiz:mergeReleaseConsumerProguardFiles
> Task :feature-quiz:mergeReleaseShaders
> Task :feature-quiz:compileReleaseShaders NO-SOURCE
> Task :feature-quiz:generateReleaseAssets UP-TO-DATE
> Task :feature-quiz:packageReleaseAssets
> Task :feature-quiz:prepareReleaseArtProfile
> Task :feature-quiz:mergeReleaseJavaResource
> Task :feature-map:mergeReleaseJniLibFolders
> Task :feature-quiz:syncReleaseLibJars
> Task :feature-quiz:bundleReleaseLocalLintAar
> Task :feature-map:mergeReleaseNativeLibs NO-SOURCE
> Task :feature-map:stripReleaseDebugSymbols NO-SOURCE
> Task :feature-map:copyReleaseJniLibsProjectAndLocalJars
> Task :feature-map:extractDeepLinksForAarRelease
> Task :feature-map:extractReleaseAnnotations
> Task :feature-map:mergeReleaseGeneratedProguardFiles
> Task :feature-map:mergeReleaseConsumerProguardFiles
> Task :feature-map:mergeReleaseShaders
> Task :feature-map:compileReleaseShaders NO-SOURCE
> Task :feature-map:generateReleaseAssets UP-TO-DATE
> Task :feature-map:packageReleaseAssets
> Task :feature-map:prepareReleaseArtProfile
> Task :feature-map:mergeReleaseJavaResource
> Task :feature-training:mergeReleaseJniLibFolders
> Task :app:kaptReleaseKotlin
> Task :feature-map:syncReleaseLibJars
> Task :feature-map:bundleReleaseLocalLintAar
> Task :feature-training:mergeReleaseNativeLibs NO-SOURCE
> Task :feature-training:stripReleaseDebugSymbols NO-SOURCE
> Task :feature-training:copyReleaseJniLibsProjectAndLocalJars
> Task :feature-training:extractDeepLinksForAarRelease
> Task :feature-training:extractReleaseAnnotations
> Task :feature-training:mergeReleaseGeneratedProguardFiles
> Task :feature-training:mergeReleaseConsumerProguardFiles
> Task :feature-training:mergeReleaseShaders
> Task :feature-training:compileReleaseShaders NO-SOURCE
> Task :feature-training:generateReleaseAssets UP-TO-DATE
> Task :feature-training:packageReleaseAssets
> Task :feature-training:prepareReleaseArtProfile
> Task :feature-training:mergeReleaseJavaResource
> Task :feature-qr:mergeReleaseJniLibFolders
> Task :feature-training:syncReleaseLibJars
> Task :feature-training:bundleReleaseLocalLintAar
> Task :feature-qr:mergeReleaseNativeLibs NO-SOURCE
> Task :feature-qr:stripReleaseDebugSymbols NO-SOURCE
> Task :feature-qr:copyReleaseJniLibsProjectAndLocalJars
> Task :feature-qr:extractDeepLinksForAarRelease
> Task :feature-qr:extractReleaseAnnotations
> Task :feature-qr:mergeReleaseGeneratedProguardFiles
> Task :feature-qr:mergeReleaseConsumerProguardFiles
> Task :feature-qr:mergeReleaseShaders
> Task :feature-qr:compileReleaseShaders NO-SOURCE
> Task :feature-qr:generateReleaseAssets UP-TO-DATE
> Task :feature-qr:packageReleaseAssets
> Task :feature-qr:prepareReleaseArtProfile
> Task :feature-qr:mergeReleaseJavaResource
> Task :feature-profile:mergeReleaseJniLibFolders
> Task :feature-qr:syncReleaseLibJars
> Task :feature-qr:bundleReleaseLocalLintAar
> Task :feature-profile:mergeReleaseNativeLibs NO-SOURCE
> Task :feature-profile:stripReleaseDebugSymbols NO-SOURCE
> Task :feature-profile:copyReleaseJniLibsProjectAndLocalJars
> Task :feature-profile:extractDeepLinksForAarRelease
> Task :feature-profile:extractReleaseAnnotations
> Task :feature-profile:mergeReleaseGeneratedProguardFiles
> Task :feature-profile:mergeReleaseConsumerProguardFiles
> Task :feature-profile:mergeReleaseShaders
> Task :feature-profile:compileReleaseShaders NO-SOURCE
> Task :feature-profile:generateReleaseAssets UP-TO-DATE
> Task :feature-profile:packageReleaseAssets
> Task :feature-profile:prepareReleaseArtProfile
> Task :feature-profile:mergeReleaseJavaResource
> Task :core-database:mergeReleaseJniLibFolders
> Task :feature-profile:syncReleaseLibJars
> Task :feature-profile:bundleReleaseLocalLintAar
> Task :core-database:mergeReleaseNativeLibs NO-SOURCE
> Task :core-database:stripReleaseDebugSymbols NO-SOURCE
> Task :core-database:copyReleaseJniLibsProjectAndLocalJars
> Task :core-database:extractDeepLinksForAarRelease
> Task :core-database:extractReleaseAnnotations
> Task :core-database:mergeReleaseGeneratedProguardFiles
> Task :core-database:mergeReleaseConsumerProguardFiles
> Task :core-database:mergeReleaseShaders
> Task :core-database:compileReleaseShaders NO-SOURCE
> Task :core-database:generateReleaseAssets UP-TO-DATE
> Task :core-database:packageReleaseAssets
> Task :core-database:prepareReleaseArtProfile
> Task :core-database:mergeReleaseJavaResource
> Task :core-storage:mergeReleaseJniLibFolders
> Task :core-database:syncReleaseLibJars
> Task :core-database:bundleReleaseLocalLintAar
> Task :core-storage:mergeReleaseNativeLibs NO-SOURCE
> Task :core-storage:stripReleaseDebugSymbols NO-SOURCE
> Task :core-storage:copyReleaseJniLibsProjectAndLocalJars
> Task :core-storage:extractDeepLinksForAarRelease
> Task :core-storage:extractReleaseAnnotations
> Task :core-storage:mergeReleaseGeneratedProguardFiles
> Task :core-storage:mergeReleaseConsumerProguardFiles
> Task :core-storage:mergeReleaseShaders
> Task :core-storage:compileReleaseShaders NO-SOURCE
> Task :core-storage:generateReleaseAssets UP-TO-DATE
> Task :core-storage:packageReleaseAssets
> Task :core-storage:prepareReleaseArtProfile
> Task :core-storage:mergeReleaseJavaResource
> Task :core-security:mergeReleaseJniLibFolders
> Task :core-storage:syncReleaseLibJars
> Task :core-storage:bundleReleaseLocalLintAar
> Task :core-security:mergeReleaseNativeLibs NO-SOURCE
> Task :core-security:stripReleaseDebugSymbols NO-SOURCE
> Task :core-security:copyReleaseJniLibsProjectAndLocalJars
> Task :core-security:extractDeepLinksForAarRelease
> Task :core-security:extractReleaseAnnotations
> Task :core-security:mergeReleaseGeneratedProguardFiles
> Task :core-security:mergeReleaseConsumerProguardFiles
> Task :core-security:mergeReleaseShaders
> Task :core-security:compileReleaseShaders NO-SOURCE
> Task :core-security:generateReleaseAssets UP-TO-DATE
> Task :core-security:packageReleaseAssets
> Task :core-security:prepareReleaseArtProfile
> Task :core-security:mergeReleaseJavaResource
> Task :core-mapping:mergeReleaseJniLibFolders
> Task :core-security:syncReleaseLibJars
> Task :core-security:bundleReleaseLocalLintAar
> Task :core-mapping:mergeReleaseNativeLibs NO-SOURCE
> Task :core-mapping:stripReleaseDebugSymbols NO-SOURCE
> Task :core-mapping:copyReleaseJniLibsProjectAndLocalJars
> Task :core-mapping:extractDeepLinksForAarRelease
> Task :core-mapping:extractReleaseAnnotations
> Task :core-mapping:mergeReleaseGeneratedProguardFiles
> Task :core-mapping:mergeReleaseConsumerProguardFiles
> Task :core-mapping:mergeReleaseShaders
> Task :core-mapping:compileReleaseShaders NO-SOURCE
> Task :core-mapping:generateReleaseAssets UP-TO-DATE
> Task :core-mapping:packageReleaseAssets
> Task :core-mapping:prepareReleaseArtProfile
> Task :core-mapping:mergeReleaseJavaResource
> Task :core-mapping:syncReleaseLibJars
> Task :core-mapping:bundleReleaseLocalLintAar
> Task :core-database:writeReleaseLintModelMetadata
> Task :core-mapping:writeReleaseLintModelMetadata
> Task :core-navigation:writeReleaseLintModelMetadata
> Task :core-security:writeReleaseLintModelMetadata
> Task :core-storage:writeReleaseLintModelMetadata
> Task :feature-map:writeReleaseLintModelMetadata
> Task :feature-profile:writeReleaseLintModelMetadata
> Task :feature-qr:writeReleaseLintModelMetadata
> Task :feature-quiz:writeReleaseLintModelMetadata
> Task :feature-training:writeReleaseLintModelMetadata
> Task :core-database:generateReleaseLintVitalModel
> Task :core-mapping:generateReleaseLintVitalModel
> Task :core-navigation:generateReleaseLintVitalModel
> Task :core-security:generateReleaseLintVitalModel
> Task :core-storage:generateReleaseLintVitalModel
> Task :feature-map:generateReleaseLintVitalModel
> Task :feature-profile:generateReleaseLintVitalModel
> Task :feature-qr:generateReleaseLintVitalModel
> Task :feature-quiz:generateReleaseLintVitalModel
> Task :feature-training:generateReleaseLintVitalModel
> Task :app:mergeReleaseJniLibFolders
> Task :core-database:copyReleaseJniLibsProjectOnly
> Task :core-mapping:copyReleaseJniLibsProjectOnly
> Task :core-navigation:copyReleaseJniLibsProjectOnly
> Task :core-security:copyReleaseJniLibsProjectOnly
> Task :core-storage:copyReleaseJniLibsProjectOnly
> Task :feature-map:copyReleaseJniLibsProjectOnly
> Task :feature-profile:copyReleaseJniLibsProjectOnly
> Task :feature-qr:copyReleaseJniLibsProjectOnly
> Task :feature-quiz:copyReleaseJniLibsProjectOnly
> Task :feature-training:copyReleaseJniLibsProjectOnly
> Task :core-navigation:exportReleaseConsumerProguardFiles
> Task :feature-quiz:exportReleaseConsumerProguardFiles
> Task :feature-map:exportReleaseConsumerProguardFiles
> Task :feature-training:exportReleaseConsumerProguardFiles
> Task :feature-qr:exportReleaseConsumerProguardFiles
> Task :feature-profile:exportReleaseConsumerProguardFiles
> Task :core-database:exportReleaseConsumerProguardFiles
> Task :core-storage:exportReleaseConsumerProguardFiles
> Task :core-security:exportReleaseConsumerProguardFiles
> Task :core-mapping:exportReleaseConsumerProguardFiles
> Task :app:checkReleaseDuplicateClasses
> Task :app:mergeReleaseStartupProfile
> Task :app:mergeReleaseShaders
> Task :app:compileReleaseShaders NO-SOURCE
> Task :app:generateReleaseAssets UP-TO-DATE
> Task :app:mergeReleaseArtProfile
> Task :app:mergeReleaseAssets
> Task :app:compressReleaseAssets
> Task :app:extractReleaseVersionControlInfo
> Task :app:mergeReleaseNativeLibs

> Task :app:stripReleaseDebugSymbols
Unable to strip the following libraries, packaging them as they are: libandroidx.graphics.path.so, libbarhopper_v3.so, libimage_processing_util_jni.so, libmaplibre.so, libsqlcipher.so, libtoolChecker.so.

> Task :app:collectReleaseDependencies
> Task :app:extractReleaseNativeSymbolTables
> Task :app:sdkReleaseDependencyData
> Task :app:mergeReleaseNativeDebugMetadata NO-SOURCE
> Task :app:writeReleaseAppMetadata
> Task :app:writeReleaseSigningConfigVersions
> Task :app:detekt UP-TO-DATE
> Task :app:loadKtlintReporters UP-TO-DATE
> Task :app:runKtlintCheckOverAndroidTestDebugSourceSet NO-SOURCE
> Task :app:ktlintAndroidTestDebugSourceSetCheck SKIPPED
> Task :app:runKtlintCheckOverAndroidTestReleaseSourceSet NO-SOURCE
> Task :app:ktlintAndroidTestReleaseSourceSetCheck SKIPPED
> Task :app:runKtlintCheckOverAndroidTestSourceSet NO-SOURCE
> Task :app:ktlintAndroidTestSourceSetCheck SKIPPED
> Task :app:runKtlintCheckOverDebugSourceSet NO-SOURCE
> Task :app:ktlintDebugSourceSetCheck SKIPPED
> Task :app:mergeExtDexDebug
> Task :app:runKtlintCheckOverKotlinScripts UP-TO-DATE
> Task :app:ktlintKotlinScriptCheck UP-TO-DATE
> Task :app:runKtlintCheckOverMainSourceSet UP-TO-DATE
> Task :app:ktlintMainSourceSetCheck UP-TO-DATE
> Task :app:runKtlintCheckOverReleaseSourceSet NO-SOURCE
> Task :app:ktlintReleaseSourceSetCheck SKIPPED
> Task :app:runKtlintCheckOverTestDebugSourceSet NO-SOURCE
> Task :app:ktlintTestDebugSourceSetCheck SKIPPED
> Task :app:runKtlintCheckOverTestFixturesDebugSourceSet NO-SOURCE
> Task :app:ktlintTestFixturesDebugSourceSetCheck SKIPPED
> Task :app:runKtlintCheckOverTestFixturesReleaseSourceSet NO-SOURCE
> Task :app:ktlintTestFixturesReleaseSourceSetCheck SKIPPED
> Task :app:runKtlintCheckOverTestFixturesSourceSet NO-SOURCE
> Task :app:ktlintTestFixturesSourceSetCheck SKIPPED
> Task :app:runKtlintCheckOverTestReleaseSourceSet NO-SOURCE
> Task :app:ktlintTestReleaseSourceSetCheck SKIPPED
> Task :app:runKtlintCheckOverTestSourceSet NO-SOURCE
> Task :app:ktlintTestSourceSetCheck SKIPPED
> Task :core-navigation:stripDebugDebugSymbols NO-SOURCE
> Task :core-navigation:copyDebugJniLibsProjectAndLocalJars
> Task :core-navigation:extractDebugAnnotations
> Task :core-navigation:extractDeepLinksForAarDebug
> Task :core-navigation:mergeDebugGeneratedProguardFiles
> Task :core-navigation:mergeDebugConsumerProguardFiles
> Task :core-navigation:prepareDebugArtProfile
> Task :core-database:stripDebugDebugSymbols NO-SOURCE
> Task :core-navigation:mergeDebugJavaResource
> Task :app:optimizeReleaseResources
> Task :core-navigation:syncDebugLibJars
> Task :core-navigation:bundleDebugLocalLintAar
> Task :core-database:copyDebugJniLibsProjectAndLocalJars
> Task :core-database:extractDebugAnnotations
> Task :core-database:extractDeepLinksForAarDebug
> Task :core-database:mergeDebugGeneratedProguardFiles
> Task :core-database:mergeDebugConsumerProguardFiles
> Task :core-database:prepareDebugArtProfile
> Task :core-storage:stripDebugDebugSymbols NO-SOURCE
> Task :core-storage:copyDebugJniLibsProjectAndLocalJars
> Task :core-database:mergeDebugJavaResource
> Task :core-storage:extractDebugAnnotations
> Task :core-database:syncDebugLibJars
> Task :core-database:bundleDebugLocalLintAar
> Task :core-storage:extractDeepLinksForAarDebug
> Task :core-storage:mergeDebugGeneratedProguardFiles
> Task :core-storage:mergeDebugConsumerProguardFiles
> Task :core-storage:prepareDebugArtProfile
> Task :core-security:stripDebugDebugSymbols NO-SOURCE
> Task :core-security:copyDebugJniLibsProjectAndLocalJars
> Task :core-storage:mergeDebugJavaResource
> Task :core-security:extractDebugAnnotations
> Task :core-storage:syncDebugLibJars
> Task :core-storage:bundleDebugLocalLintAar
> Task :core-security:extractDeepLinksForAarDebug
> Task :core-security:mergeDebugGeneratedProguardFiles
> Task :core-security:mergeDebugConsumerProguardFiles
> Task :core-security:prepareDebugArtProfile
> Task :core-mapping:stripDebugDebugSymbols NO-SOURCE
> Task :core-mapping:copyDebugJniLibsProjectAndLocalJars
> Task :core-security:mergeDebugJavaResource
> Task :core-mapping:extractDebugAnnotations
> Task :core-security:syncDebugLibJars
> Task :core-security:bundleDebugLocalLintAar
> Task :core-mapping:extractDeepLinksForAarDebug
> Task :core-mapping:mergeDebugGeneratedProguardFiles
> Task :core-mapping:mergeDebugConsumerProguardFiles
> Task :core-mapping:prepareDebugArtProfile
> Task :feature-quiz:stripDebugDebugSymbols NO-SOURCE
> Task :feature-quiz:copyDebugJniLibsProjectAndLocalJars
> Task :core-mapping:mergeDebugJavaResource
> Task :feature-quiz:extractDebugAnnotations
> Task :core-mapping:syncDebugLibJars
> Task :core-mapping:bundleDebugLocalLintAar
> Task :feature-quiz:extractDeepLinksForAarDebug
> Task :feature-quiz:mergeDebugGeneratedProguardFiles
> Task :feature-quiz:mergeDebugConsumerProguardFiles
> Task :feature-quiz:prepareDebugArtProfile
> Task :feature-map:stripDebugDebugSymbols NO-SOURCE
> Task :feature-map:copyDebugJniLibsProjectAndLocalJars
> Task :feature-quiz:mergeDebugJavaResource
> Task :feature-map:extractDebugAnnotations
> Task :feature-quiz:syncDebugLibJars
> Task :feature-quiz:bundleDebugLocalLintAar
> Task :feature-map:extractDeepLinksForAarDebug
> Task :feature-map:mergeDebugGeneratedProguardFiles
> Task :feature-map:mergeDebugConsumerProguardFiles
> Task :feature-map:prepareDebugArtProfile
> Task :feature-training:stripDebugDebugSymbols NO-SOURCE
> Task :feature-training:copyDebugJniLibsProjectAndLocalJars
> Task :feature-map:mergeDebugJavaResource
> Task :feature-training:extractDebugAnnotations
> Task :feature-map:syncDebugLibJars
> Task :feature-map:bundleDebugLocalLintAar
> Task :feature-training:extractDeepLinksForAarDebug
> Task :feature-training:mergeDebugGeneratedProguardFiles
> Task :feature-training:mergeDebugConsumerProguardFiles
> Task :feature-training:prepareDebugArtProfile
> Task :feature-qr:stripDebugDebugSymbols NO-SOURCE
> Task :feature-qr:copyDebugJniLibsProjectAndLocalJars
> Task :feature-training:mergeDebugJavaResource
> Task :feature-qr:extractDebugAnnotations
> Task :feature-training:syncDebugLibJars
> Task :feature-training:bundleDebugLocalLintAar
> Task :feature-qr:extractDeepLinksForAarDebug
> Task :feature-qr:mergeDebugGeneratedProguardFiles
> Task :feature-qr:mergeDebugConsumerProguardFiles
> Task :feature-qr:prepareDebugArtProfile
> Task :feature-profile:stripDebugDebugSymbols NO-SOURCE
> Task :feature-profile:copyDebugJniLibsProjectAndLocalJars
> Task :feature-qr:mergeDebugJavaResource
> Task :feature-profile:extractDebugAnnotations
> Task :feature-qr:syncDebugLibJars
> Task :feature-qr:bundleDebugLocalLintAar
> Task :feature-profile:extractDeepLinksForAarDebug
> Task :feature-profile:mergeDebugGeneratedProguardFiles
> Task :feature-profile:mergeDebugConsumerProguardFiles
> Task :feature-profile:prepareDebugArtProfile
> Task :feature-profile:mergeDebugJavaResource
> Task :app:packageDebug
> Task :app:createDebugApkListingFileRedirect
> Task :app:assembleDebug
> Task :feature-profile:syncDebugLibJars
> Task :feature-profile:bundleDebugLocalLintAar
> Task :app:preDebugAndroidTestBuild SKIPPED
> Task :app:generateDebugAndroidTestResValues
> Task :core-database:createFullJarDebug
> Task :core-database:writeDebugLintModelMetadata
> Task :core-mapping:createFullJarDebug
> Task :core-mapping:writeDebugLintModelMetadata
> Task :core-navigation:createFullJarDebug
> Task :core-navigation:writeDebugLintModelMetadata
> Task :core-security:createFullJarDebug
> Task :app:bundleDebugClassesToCompileJar
> Task :core-security:writeDebugLintModelMetadata
> Task :core-storage:createFullJarDebug
> Task :core-storage:writeDebugLintModelMetadata
> Task :feature-map:createFullJarDebug
> Task :feature-map:writeDebugLintModelMetadata
> Task :feature-profile:createFullJarDebug
> Task :feature-profile:writeDebugLintModelMetadata
> Task :feature-qr:createFullJarDebug
> Task :feature-qr:writeDebugLintModelMetadata
> Task :feature-quiz:createFullJarDebug
> Task :feature-quiz:writeDebugLintModelMetadata
> Task :feature-training:createFullJarDebug
> Task :feature-training:writeDebugLintModelMetadata
> Task :app:generateDebugAndroidTestLintModel
> Task :core-database:generateDebugLintModel
> Task :core-mapping:generateDebugLintModel
> Task :core-navigation:generateDebugLintModel
> Task :core-security:generateDebugLintModel
> Task :core-storage:generateDebugLintModel
> Task :feature-map:generateDebugLintModel
> Task :feature-profile:generateDebugLintModel
> Task :feature-qr:generateDebugLintModel
> Task :feature-quiz:generateDebugLintModel
> Task :feature-training:generateDebugLintModel
> Task :app:generateDebugLintReportModel
> Task :app:preDebugUnitTestBuild UP-TO-DATE
> Task :app:generateDebugUnitTestLintModel
> Task :core-database:preDebugAndroidTestBuild UP-TO-DATE
> Task :core-database:generateDebugAndroidTestResValues
> Task :core-database:generateDebugAndroidTestLintModel
> Task :core-database:preDebugUnitTestBuild UP-TO-DATE
> Task :core-database:generateDebugUnitTestLintModel
> Task :core-mapping:preDebugAndroidTestBuild UP-TO-DATE
> Task :core-mapping:generateDebugAndroidTestResValues
> Task :core-mapping:generateDebugAndroidTestLintModel
> Task :core-mapping:preDebugUnitTestBuild UP-TO-DATE
> Task :core-mapping:generateDebugUnitTestLintModel
> Task :core-navigation:preDebugAndroidTestBuild UP-TO-DATE
> Task :core-navigation:generateDebugAndroidTestResValues
> Task :core-navigation:generateDebugAndroidTestLintModel
> Task :core-navigation:preDebugUnitTestBuild UP-TO-DATE
> Task :core-navigation:generateDebugUnitTestLintModel
> Task :core-security:preDebugAndroidTestBuild UP-TO-DATE
> Task :core-security:generateDebugAndroidTestResValues
> Task :core-security:generateDebugAndroidTestLintModel
> Task :core-security:preDebugUnitTestBuild UP-TO-DATE
> Task :core-security:generateDebugUnitTestLintModel
> Task :core-storage:preDebugAndroidTestBuild UP-TO-DATE
> Task :core-storage:generateDebugAndroidTestResValues
> Task :core-storage:generateDebugAndroidTestLintModel
> Task :core-storage:preDebugUnitTestBuild UP-TO-DATE
> Task :core-storage:generateDebugUnitTestLintModel
> Task :feature-map:preDebugAndroidTestBuild UP-TO-DATE
> Task :feature-map:generateDebugAndroidTestResValues
> Task :feature-map:generateDebugAndroidTestLintModel
> Task :feature-map:preDebugUnitTestBuild UP-TO-DATE
> Task :feature-map:generateDebugUnitTestLintModel
> Task :feature-profile:preDebugAndroidTestBuild UP-TO-DATE
> Task :feature-profile:generateDebugAndroidTestResValues
> Task :feature-profile:generateDebugAndroidTestLintModel
> Task :feature-profile:preDebugUnitTestBuild UP-TO-DATE
> Task :feature-profile:generateDebugUnitTestLintModel
> Task :feature-qr:preDebugAndroidTestBuild UP-TO-DATE
> Task :feature-qr:generateDebugAndroidTestResValues
> Task :feature-qr:generateDebugAndroidTestLintModel
> Task :feature-qr:preDebugUnitTestBuild UP-TO-DATE
> Task :feature-qr:generateDebugUnitTestLintModel
> Task :feature-quiz:preDebugAndroidTestBuild UP-TO-DATE
> Task :feature-quiz:generateDebugAndroidTestResValues
> Task :feature-quiz:generateDebugAndroidTestLintModel
> Task :feature-quiz:preDebugUnitTestBuild UP-TO-DATE
> Task :feature-quiz:generateDebugUnitTestLintModel
> Task :feature-training:preDebugAndroidTestBuild UP-TO-DATE
> Task :feature-training:generateDebugAndroidTestResValues
> Task :feature-training:generateDebugAndroidTestLintModel
> Task :feature-training:preDebugUnitTestBuild UP-TO-DATE
> Task :feature-training:generateDebugUnitTestLintModel
> Task :app:bundleDebugClassesToRuntimeJar
> Task :app:kaptGenerateStubsDebugUnitTestKotlin
> Task :app:javaPreCompileDebugUnitTest UP-TO-DATE
> Task :app:preReleaseUnitTestBuild UP-TO-DATE
> Task :app:javaPreCompileReleaseUnitTest UP-TO-DATE
> Task :core-database:bundleDebugAar
> Task :core-database:assembleDebug
> Task :core-database:bundleReleaseAar
> Task :core-database:mapReleaseSourceSetPaths
> Task :app:kaptDebugUnitTestKotlin
> Task :core-mapping:lintVitalAnalyzeRelease
> Task :core-database:mergeReleaseResources
> Task :app:compileReleaseKotlin
> Task :core-navigation:lintVitalAnalyzeRelease
> Task :app:compileReleaseJavaWithJavac
> Task :app:compileDebugUnitTestKotlin NO-SOURCE
> Task :app:compileDebugUnitTestJavaWithJavac NO-SOURCE
> Task :app:hiltAggregateDepsRelease
> Task :app:hiltAggregateDepsDebugUnitTest
> Task :core-database:lintVitalAnalyzeRelease

> Task :app:hiltJavaCompileRelease
Note: /home/yano4/project3/app/build/generated/hilt/component_sources/release/com/safeplant/Hilt_SafePlantApplication.java uses or overrides a deprecated API.
Note: Recompile with -Xlint:deprecation for details.

> Task :core-security:lintVitalAnalyzeRelease
> Task :app:generateReleaseLintVitalReportModel
> Task :app:processReleaseJavaRes
> Task :app:hiltJavaCompileDebugUnitTest NO-SOURCE
> Task :app:processDebugUnitTestJavaRes NO-SOURCE
> Task :app:transformReleaseClassesWithAsm
> Task :app:bundleReleaseClassesToCompileJar
> Task :app:transformDebugUnitTestClassesWithAsm
> Task :core-storage:lintVitalAnalyzeRelease
> Task :app:mergeReleaseGeneratedProguardFiles
> Task :app:testDebugUnitTest NO-SOURCE
> Task :core-database:verifyReleaseResources
> Task :app:bundleReleaseClassesToRuntimeJar
> Task :app:kaptGenerateStubsReleaseUnitTestKotlin
> Task :app:mergeReleaseJavaResource
> Task :core-database:assembleRelease
> Task :core-database:assemble
> Task :core-database:generateDebugLintReportModel
> Task :core-database:generateDebugUnitTestStubRFile UP-TO-DATE
> Task :core-database:kspDebugUnitTestKotlin NO-SOURCE
> Task :app:kaptReleaseUnitTestKotlin
> Task :core-database:kaptGenerateStubsDebugUnitTestKotlin UP-TO-DATE
> Task :app:compileReleaseUnitTestKotlin NO-SOURCE
> Task :app:compileReleaseUnitTestJavaWithJavac NO-SOURCE
> Task :core-database:kaptDebugUnitTestKotlin UP-TO-DATE
> Task :app:hiltAggregateDepsReleaseUnitTest
> Task :core-database:compileDebugUnitTestKotlin NO-SOURCE
> Task :app:hiltJavaCompileReleaseUnitTest NO-SOURCE
> Task :app:processReleaseUnitTestJavaRes NO-SOURCE
> Task :app:transformReleaseUnitTestClassesWithAsm
> Task :core-database:javaPreCompileDebugUnitTest UP-TO-DATE
> Task :app:testReleaseUnitTest NO-SOURCE
> Task :app:test UP-TO-DATE
> Task :core-database:compileDebugUnitTestJavaWithJavac NO-SOURCE
> Task :core-database:hiltAggregateDepsDebugUnitTest UP-TO-DATE
> Task :core-database:hiltJavaCompileDebugUnitTest NO-SOURCE
> Task :core-database:processDebugUnitTestJavaRes NO-SOURCE
> Task :core-database:transformDebugUnitTestClassesWithAsm UP-TO-DATE
> Task :core-database:testDebugUnitTest NO-SOURCE
> Task :core-database:preReleaseUnitTestBuild UP-TO-DATE
> Task :core-database:generateReleaseUnitTestStubRFile UP-TO-DATE
> Task :core-database:kspReleaseUnitTestKotlin NO-SOURCE
> Task :core-database:kaptGenerateStubsReleaseUnitTestKotlin UP-TO-DATE
> Task :core-database:kaptReleaseUnitTestKotlin UP-TO-DATE
> Task :core-database:compileReleaseUnitTestKotlin NO-SOURCE
> Task :core-database:javaPreCompileReleaseUnitTest UP-TO-DATE
> Task :core-database:compileReleaseUnitTestJavaWithJavac NO-SOURCE
> Task :core-database:hiltAggregateDepsReleaseUnitTest UP-TO-DATE
> Task :core-database:hiltJavaCompileReleaseUnitTest NO-SOURCE
> Task :core-database:processReleaseUnitTestJavaRes NO-SOURCE
> Task :core-database:transformReleaseUnitTestClassesWithAsm UP-TO-DATE
> Task :core-database:testReleaseUnitTest NO-SOURCE
> Task :core-database:test UP-TO-DATE
> Task :core-mapping:bundleDebugAar
> Task :core-mapping:assembleDebug
> Task :core-mapping:bundleReleaseAar
> Task :core-mapping:mapReleaseSourceSetPaths
> Task :app:expandReleaseArtProfileWildcards
> Task :core-mapping:mergeReleaseResources
> Task :feature-profile:lintVitalAnalyzeRelease
> Task :feature-map:lintVitalAnalyzeRelease

> Task :app:minifyReleaseWithR8
Supplied proguard configuration does not exist: /home/yano4/project3/app/proguard-rules.pro

> Task :core-mapping:generateDebugLintReportModel
> Task :core-mapping:generateDebugUnitTestStubRFile UP-TO-DATE
> Task :core-mapping:kaptGenerateStubsDebugUnitTestKotlin UP-TO-DATE
> Task :core-mapping:kaptDebugUnitTestKotlin UP-TO-DATE
> Task :core-mapping:compileDebugUnitTestKotlin NO-SOURCE
> Task :core-mapping:javaPreCompileDebugUnitTest UP-TO-DATE
> Task :core-mapping:compileDebugUnitTestJavaWithJavac NO-SOURCE
> Task :core-mapping:hiltAggregateDepsDebugUnitTest UP-TO-DATE
> Task :core-mapping:hiltJavaCompileDebugUnitTest NO-SOURCE
> Task :core-mapping:processDebugUnitTestJavaRes NO-SOURCE
> Task :core-mapping:transformDebugUnitTestClassesWithAsm UP-TO-DATE
> Task :core-mapping:testDebugUnitTest NO-SOURCE
> Task :core-mapping:preReleaseUnitTestBuild UP-TO-DATE
> Task :core-mapping:generateReleaseUnitTestStubRFile UP-TO-DATE
> Task :core-mapping:kaptGenerateStubsReleaseUnitTestKotlin UP-TO-DATE
> Task :core-mapping:kaptReleaseUnitTestKotlin UP-TO-DATE
> Task :core-mapping:compileReleaseUnitTestKotlin NO-SOURCE
> Task :core-mapping:javaPreCompileReleaseUnitTest UP-TO-DATE
> Task :core-mapping:compileReleaseUnitTestJavaWithJavac NO-SOURCE
> Task :core-mapping:hiltAggregateDepsReleaseUnitTest UP-TO-DATE
> Task :core-mapping:hiltJavaCompileReleaseUnitTest NO-SOURCE
> Task :core-mapping:processReleaseUnitTestJavaRes NO-SOURCE
> Task :core-mapping:transformReleaseUnitTestClassesWithAsm UP-TO-DATE
> Task :core-mapping:testReleaseUnitTest NO-SOURCE
> Task :core-mapping:test UP-TO-DATE
> Task :feature-qr:lintVitalAnalyzeRelease
> Task :core-navigation:bundleDebugAar
> Task :core-navigation:assembleDebug
> Task :core-navigation:bundleReleaseAar
> Task :core-navigation:mapReleaseSourceSetPaths
> Task :core-mapping:verifyReleaseResources
> Task :core-navigation:mergeReleaseResources
> Task :core-mapping:assembleRelease
> Task :core-mapping:assemble
> Task :core-navigation:generateDebugLintReportModel
> Task :core-navigation:generateDebugUnitTestStubRFile UP-TO-DATE
> Task :core-navigation:kaptGenerateStubsDebugUnitTestKotlin UP-TO-DATE
> Task :core-navigation:kaptDebugUnitTestKotlin UP-TO-DATE
> Task :core-navigation:compileDebugUnitTestKotlin NO-SOURCE
> Task :core-navigation:javaPreCompileDebugUnitTest UP-TO-DATE
> Task :core-navigation:compileDebugUnitTestJavaWithJavac NO-SOURCE
> Task :core-navigation:hiltAggregateDepsDebugUnitTest UP-TO-DATE
> Task :core-navigation:hiltJavaCompileDebugUnitTest NO-SOURCE
> Task :core-navigation:processDebugUnitTestJavaRes NO-SOURCE
> Task :core-navigation:transformDebugUnitTestClassesWithAsm UP-TO-DATE
> Task :core-navigation:testDebugUnitTest NO-SOURCE
> Task :core-navigation:preReleaseUnitTestBuild UP-TO-DATE
> Task :core-navigation:generateReleaseUnitTestStubRFile UP-TO-DATE
> Task :core-navigation:kaptGenerateStubsReleaseUnitTestKotlin UP-TO-DATE
> Task :core-navigation:kaptReleaseUnitTestKotlin UP-TO-DATE
> Task :core-navigation:compileReleaseUnitTestKotlin NO-SOURCE
> Task :core-navigation:javaPreCompileReleaseUnitTest UP-TO-DATE
> Task :core-navigation:compileReleaseUnitTestJavaWithJavac NO-SOURCE
> Task :core-navigation:hiltAggregateDepsReleaseUnitTest UP-TO-DATE
> Task :core-navigation:hiltJavaCompileReleaseUnitTest NO-SOURCE
> Task :core-navigation:processReleaseUnitTestJavaRes NO-SOURCE
> Task :core-navigation:transformReleaseUnitTestClassesWithAsm UP-TO-DATE
> Task :core-navigation:testReleaseUnitTest NO-SOURCE
> Task :core-navigation:test UP-TO-DATE
> Task :core-navigation:verifyReleaseResources
> Task :core-security:bundleDebugAar
> Task :core-navigation:assembleRelease
> Task :core-navigation:assemble
> Task :core-security:assembleDebug
> Task :core-security:bundleReleaseAar
> Task :core-security:mapReleaseSourceSetPaths
> Task :core-security:mergeReleaseResources
> Task :core-security:generateDebugLintReportModel
> Task :core-security:generateDebugUnitTestStubRFile UP-TO-DATE
> Task :core-security:kaptGenerateStubsDebugUnitTestKotlin UP-TO-DATE
> Task :core-security:kaptDebugUnitTestKotlin UP-TO-DATE
> Task :core-security:compileDebugUnitTestKotlin NO-SOURCE
> Task :core-security:javaPreCompileDebugUnitTest UP-TO-DATE
> Task :core-security:compileDebugUnitTestJavaWithJavac NO-SOURCE
> Task :core-security:hiltAggregateDepsDebugUnitTest UP-TO-DATE
> Task :core-security:hiltJavaCompileDebugUnitTest NO-SOURCE
> Task :core-security:processDebugUnitTestJavaRes NO-SOURCE
> Task :core-security:transformDebugUnitTestClassesWithAsm UP-TO-DATE
> Task :core-security:testDebugUnitTest NO-SOURCE
> Task :core-security:preReleaseUnitTestBuild UP-TO-DATE
> Task :core-security:generateReleaseUnitTestStubRFile UP-TO-DATE
> Task :core-security:verifyReleaseResources
> Task :core-security:kaptGenerateStubsReleaseUnitTestKotlin UP-TO-DATE
> Task :core-security:assembleRelease
> Task :core-security:assemble
> Task :core-security:kaptReleaseUnitTestKotlin UP-TO-DATE
> Task :core-security:compileReleaseUnitTestKotlin NO-SOURCE
> Task :core-security:javaPreCompileReleaseUnitTest UP-TO-DATE
> Task :core-security:compileReleaseUnitTestJavaWithJavac NO-SOURCE
> Task :core-security:hiltAggregateDepsReleaseUnitTest UP-TO-DATE
> Task :core-security:hiltJavaCompileReleaseUnitTest NO-SOURCE
> Task :core-security:processReleaseUnitTestJavaRes NO-SOURCE
> Task :core-security:transformReleaseUnitTestClassesWithAsm UP-TO-DATE
> Task :core-security:testReleaseUnitTest NO-SOURCE
> Task :core-security:test UP-TO-DATE
> Task :feature-quiz:lintVitalAnalyzeRelease
> Task :core-storage:bundleDebugAar
> Task :core-storage:assembleDebug
> Task :core-storage:bundleReleaseAar
> Task :core-storage:mapReleaseSourceSetPaths
> Task :feature-training:lintVitalAnalyzeRelease
> Task :core-storage:mergeReleaseResources
> Task :core-storage:generateDebugLintReportModel
> Task :core-storage:generateDebugUnitTestStubRFile UP-TO-DATE
> Task :core-storage:kaptGenerateStubsDebugUnitTestKotlin UP-TO-DATE
> Task :core-storage:kaptDebugUnitTestKotlin UP-TO-DATE
> Task :core-storage:compileDebugUnitTestKotlin NO-SOURCE
> Task :core-storage:javaPreCompileDebugUnitTest UP-TO-DATE
> Task :core-storage:compileDebugUnitTestJavaWithJavac NO-SOURCE
> Task :core-storage:hiltAggregateDepsDebugUnitTest UP-TO-DATE
> Task :core-storage:hiltJavaCompileDebugUnitTest NO-SOURCE
> Task :core-storage:processDebugUnitTestJavaRes NO-SOURCE
> Task :core-storage:transformDebugUnitTestClassesWithAsm UP-TO-DATE
> Task :core-storage:testDebugUnitTest NO-SOURCE
> Task :core-storage:preReleaseUnitTestBuild UP-TO-DATE
> Task :core-storage:generateReleaseUnitTestStubRFile UP-TO-DATE
> Task :core-storage:verifyReleaseResources
> Task :core-storage:kaptGenerateStubsReleaseUnitTestKotlin UP-TO-DATE
> Task :core-storage:assembleRelease
> Task :core-storage:assemble
> Task :core-storage:kaptReleaseUnitTestKotlin UP-TO-DATE
> Task :core-storage:compileReleaseUnitTestKotlin NO-SOURCE
> Task :core-storage:javaPreCompileReleaseUnitTest UP-TO-DATE
> Task :core-storage:compileReleaseUnitTestJavaWithJavac NO-SOURCE
> Task :core-storage:hiltAggregateDepsReleaseUnitTest UP-TO-DATE
> Task :core-storage:hiltJavaCompileReleaseUnitTest NO-SOURCE
> Task :core-storage:processReleaseUnitTestJavaRes NO-SOURCE
> Task :core-storage:transformReleaseUnitTestClassesWithAsm UP-TO-DATE
> Task :core-storage:testReleaseUnitTest NO-SOURCE
> Task :core-storage:test UP-TO-DATE
> Task :feature-map:bundleDebugAar
> Task :feature-map:assembleDebug
> Task :feature-map:bundleReleaseAar
> Task :feature-map:mapReleaseSourceSetPaths
> Task :core-mapping:lintAnalyzeDebug
> Task :feature-map:mergeReleaseResources
> Task :feature-map:generateDebugLintReportModel
> Task :feature-map:generateDebugUnitTestStubRFile UP-TO-DATE
> Task :feature-map:kaptGenerateStubsDebugUnitTestKotlin UP-TO-DATE
> Task :feature-map:kaptDebugUnitTestKotlin UP-TO-DATE
> Task :feature-map:compileDebugUnitTestKotlin NO-SOURCE
> Task :feature-map:javaPreCompileDebugUnitTest UP-TO-DATE
> Task :feature-map:compileDebugUnitTestJavaWithJavac NO-SOURCE
> Task :feature-map:hiltAggregateDepsDebugUnitTest UP-TO-DATE
> Task :feature-map:hiltJavaCompileDebugUnitTest NO-SOURCE
> Task :feature-map:processDebugUnitTestJavaRes NO-SOURCE
> Task :feature-map:transformDebugUnitTestClassesWithAsm UP-TO-DATE
> Task :feature-map:testDebugUnitTest NO-SOURCE
> Task :feature-map:preReleaseUnitTestBuild UP-TO-DATE
> Task :feature-map:generateReleaseUnitTestStubRFile UP-TO-DATE
> Task :feature-map:kaptGenerateStubsReleaseUnitTestKotlin UP-TO-DATE
> Task :feature-map:kaptReleaseUnitTestKotlin UP-TO-DATE
> Task :feature-map:compileReleaseUnitTestKotlin NO-SOURCE
> Task :feature-map:javaPreCompileReleaseUnitTest UP-TO-DATE
> Task :feature-map:compileReleaseUnitTestJavaWithJavac NO-SOURCE
> Task :feature-map:hiltAggregateDepsReleaseUnitTest UP-TO-DATE
> Task :feature-map:hiltJavaCompileReleaseUnitTest NO-SOURCE
> Task :feature-map:processReleaseUnitTestJavaRes NO-SOURCE
> Task :feature-map:transformReleaseUnitTestClassesWithAsm UP-TO-DATE
> Task :feature-map:testReleaseUnitTest NO-SOURCE
> Task :feature-map:test UP-TO-DATE
> Task :feature-profile:bundleDebugAar
> Task :feature-profile:assembleDebug
> Task :feature-profile:bundleReleaseAar
> Task :feature-profile:mapReleaseSourceSetPaths
> Task :feature-map:verifyReleaseResources
> Task :feature-profile:mergeReleaseResources
> Task :feature-map:assembleRelease
> Task :feature-map:assemble
> Task :feature-profile:generateDebugLintReportModel
> Task :feature-profile:generateDebugUnitTestStubRFile UP-TO-DATE
> Task :feature-profile:kaptGenerateStubsDebugUnitTestKotlin UP-TO-DATE
> Task :feature-profile:kaptDebugUnitTestKotlin UP-TO-DATE
> Task :feature-profile:compileDebugUnitTestKotlin NO-SOURCE
> Task :feature-profile:javaPreCompileDebugUnitTest UP-TO-DATE
> Task :feature-profile:compileDebugUnitTestJavaWithJavac NO-SOURCE
> Task :feature-profile:hiltAggregateDepsDebugUnitTest UP-TO-DATE
> Task :feature-profile:hiltJavaCompileDebugUnitTest NO-SOURCE
> Task :feature-profile:processDebugUnitTestJavaRes NO-SOURCE
> Task :feature-profile:transformDebugUnitTestClassesWithAsm UP-TO-DATE
> Task :feature-profile:testDebugUnitTest NO-SOURCE
> Task :feature-profile:preReleaseUnitTestBuild UP-TO-DATE
> Task :feature-profile:generateReleaseUnitTestStubRFile UP-TO-DATE
> Task :feature-profile:kaptGenerateStubsReleaseUnitTestKotlin UP-TO-DATE
> Task :feature-profile:kaptReleaseUnitTestKotlin UP-TO-DATE
> Task :feature-profile:compileReleaseUnitTestKotlin NO-SOURCE
> Task :feature-profile:javaPreCompileReleaseUnitTest UP-TO-DATE
> Task :feature-profile:compileReleaseUnitTestJavaWithJavac NO-SOURCE
> Task :feature-profile:hiltAggregateDepsReleaseUnitTest UP-TO-DATE
> Task :feature-profile:hiltJavaCompileReleaseUnitTest NO-SOURCE
> Task :feature-profile:processReleaseUnitTestJavaRes NO-SOURCE
> Task :feature-profile:transformReleaseUnitTestClassesWithAsm UP-TO-DATE
> Task :feature-profile:testReleaseUnitTest NO-SOURCE
> Task :feature-profile:test UP-TO-DATE
> Task :feature-qr:bundleDebugAar
> Task :feature-qr:assembleDebug
> Task :feature-profile:verifyReleaseResources
> Task :feature-qr:bundleReleaseAar
> Task :feature-profile:assembleRelease
> Task :feature-profile:assemble
> Task :feature-qr:mapReleaseSourceSetPaths
> Task :app:lintVitalAnalyzeRelease
> Task :core-database:lintAnalyzeDebug
> Task :feature-qr:mergeReleaseResources
> Task :app:lintVitalReportRelease SKIPPED
> Task :app:lintVitalRelease SKIPPED
> Task :feature-qr:generateDebugLintReportModel
> Task :feature-qr:generateDebugUnitTestStubRFile UP-TO-DATE
> Task :feature-qr:kaptGenerateStubsDebugUnitTestKotlin UP-TO-DATE
> Task :feature-qr:kaptDebugUnitTestKotlin UP-TO-DATE
> Task :feature-qr:compileDebugUnitTestKotlin NO-SOURCE
> Task :feature-qr:javaPreCompileDebugUnitTest UP-TO-DATE
> Task :feature-qr:compileDebugUnitTestJavaWithJavac NO-SOURCE
> Task :core-navigation:lintAnalyzeDebug
> Task :feature-qr:hiltAggregateDepsDebugUnitTest UP-TO-DATE
> Task :feature-qr:hiltJavaCompileDebugUnitTest NO-SOURCE
> Task :feature-qr:processDebugUnitTestJavaRes NO-SOURCE
> Task :feature-qr:preReleaseUnitTestBuild UP-TO-DATE
> Task :feature-qr:generateReleaseUnitTestStubRFile UP-TO-DATE
> Task :feature-qr:transformDebugUnitTestClassesWithAsm
> Task :feature-qr:kaptGenerateStubsReleaseUnitTestKotlin
> Task :feature-qr:testDebugUnitTest NO-SOURCE
> Task :feature-qr:javaPreCompileReleaseUnitTest UP-TO-DATE
> Task :feature-quiz:bundleDebugAar
> Task :feature-quiz:assembleDebug
> Task :feature-quiz:bundleReleaseAar
> Task :feature-quiz:mapReleaseSourceSetPaths
> Task :feature-qr:kaptReleaseUnitTestKotlin
> Task :feature-qr:verifyReleaseResources
> Task :feature-quiz:mergeReleaseResources
> Task :feature-qr:assembleRelease
> Task :feature-qr:assemble
> Task :feature-qr:compileReleaseUnitTestKotlin NO-SOURCE
> Task :feature-qr:compileReleaseUnitTestJavaWithJavac NO-SOURCE
> Task :feature-qr:hiltAggregateDepsReleaseUnitTest
> Task :feature-qr:hiltJavaCompileReleaseUnitTest NO-SOURCE
> Task :feature-qr:processReleaseUnitTestJavaRes NO-SOURCE
> Task :feature-qr:transformReleaseUnitTestClassesWithAsm
> Task :feature-quiz:generateDebugLintReportModel
> Task :feature-qr:testReleaseUnitTest NO-SOURCE
> Task :feature-qr:test UP-TO-DATE
> Task :feature-quiz:generateDebugUnitTestStubRFile UP-TO-DATE
> Task :feature-quiz:kaptGenerateStubsDebugUnitTestKotlin UP-TO-DATE
> Task :core-security:lintAnalyzeDebug
> Task :core-storage:lintAnalyzeDebug
> Task :feature-quiz:javaPreCompileDebugUnitTest UP-TO-DATE
> Task :feature-quiz:preReleaseUnitTestBuild UP-TO-DATE
> Task :feature-quiz:kaptDebugUnitTestKotlin
> Task :feature-quiz:generateReleaseUnitTestStubRFile UP-TO-DATE
> Task :feature-quiz:compileDebugUnitTestKotlin NO-SOURCE
> Task :feature-quiz:compileDebugUnitTestJavaWithJavac NO-SOURCE
> Task :feature-quiz:hiltAggregateDepsDebugUnitTest
> Task :feature-quiz:kaptGenerateStubsReleaseUnitTestKotlin
> Task :feature-quiz:hiltJavaCompileDebugUnitTest NO-SOURCE
> Task :feature-quiz:processDebugUnitTestJavaRes NO-SOURCE
> Task :feature-quiz:transformDebugUnitTestClassesWithAsm
> Task :feature-quiz:testDebugUnitTest NO-SOURCE
> Task :feature-quiz:javaPreCompileReleaseUnitTest UP-TO-DATE
> Task :feature-quiz:kaptReleaseUnitTestKotlin
> Task :feature-training:bundleDebugAar
> Task :feature-quiz:compileReleaseUnitTestKotlin NO-SOURCE
> Task :feature-quiz:compileReleaseUnitTestJavaWithJavac NO-SOURCE
> Task :feature-training:assembleDebug
> Task :feature-quiz:hiltAggregateDepsReleaseUnitTest
> Task :feature-training:bundleReleaseAar
> Task :feature-quiz:hiltJavaCompileReleaseUnitTest NO-SOURCE
> Task :feature-quiz:processReleaseUnitTestJavaRes NO-SOURCE
> Task :feature-quiz:transformReleaseUnitTestClassesWithAsm
> Task :feature-training:mapReleaseSourceSetPaths
> Task :feature-quiz:testReleaseUnitTest NO-SOURCE
> Task :feature-quiz:test UP-TO-DATE
> Task :feature-quiz:verifyReleaseResources
> Task :feature-map:lintAnalyzeDebug
> Task :feature-training:mergeReleaseResources
> Task :feature-quiz:assembleRelease
> Task :feature-quiz:assemble
> Task :feature-training:generateDebugLintReportModel
> Task :feature-training:generateDebugUnitTestStubRFile UP-TO-DATE
> Task :feature-training:kaptGenerateStubsDebugUnitTestKotlin
> Task :feature-training:javaPreCompileDebugUnitTest UP-TO-DATE
> Task :feature-training:preReleaseUnitTestBuild UP-TO-DATE
> Task :feature-training:generateReleaseUnitTestStubRFile UP-TO-DATE
> Task :feature-training:kaptGenerateStubsReleaseUnitTestKotlin
> Task :feature-training:kaptDebugUnitTestKotlin
> Task :feature-training:compileDebugUnitTestKotlin NO-SOURCE
> Task :feature-training:compileDebugUnitTestJavaWithJavac NO-SOURCE
> Task :feature-training:kaptReleaseUnitTestKotlin
  ⚠ Command timed out after 120s
  ✗ exit code -1

$ ./gradlew test
> Task :app:preBuild UP-TO-DATE
> Task :app:preDebugBuild UP-TO-DATE
> Task :app:checkKotlinGradlePluginConfigurationErrors SKIPPED
> Task :core-database:preBuild UP-TO-DATE
> Task :core-database:preDebugBuild UP-TO-DATE
> Task :core-database:writeDebugAarMetadata UP-TO-DATE
> Task :core-mapping:preBuild UP-TO-DATE
> Task :core-mapping:preDebugBuild UP-TO-DATE
> Task :core-mapping:writeDebugAarMetadata UP-TO-DATE
> Task :core-navigation:preBuild UP-TO-DATE
> Task :core-navigation:preDebugBuild UP-TO-DATE
> Task :core-navigation:writeDebugAarMetadata UP-TO-DATE
> Task :core-security:preBuild UP-TO-DATE
> Task :core-security:preDebugBuild UP-TO-DATE
> Task :core-security:writeDebugAarMetadata UP-TO-DATE
> Task :core-storage:preBuild UP-TO-DATE
> Task :core-storage:preDebugBuild UP-TO-DATE
> Task :core-storage:writeDebugAarMetadata UP-TO-DATE
> Task :feature-map:preBuild UP-TO-DATE
> Task :feature-map:preDebugBuild UP-TO-DATE
> Task :feature-map:writeDebugAarMetadata UP-TO-DATE
> Task :feature-profile:preBuild UP-TO-DATE
> Task :feature-profile:preDebugBuild UP-TO-DATE
> Task :feature-profile:writeDebugAarMetadata UP-TO-DATE
> Task :feature-qr:preBuild UP-TO-DATE
> Task :feature-qr:preDebugBuild UP-TO-DATE
> Task :feature-qr:writeDebugAarMetadata UP-TO-DATE
> Task :feature-quiz:preBuild UP-TO-DATE
> Task :feature-quiz:preDebugBuild UP-TO-DATE
> Task :feature-quiz:writeDebugAarMetadata UP-TO-DATE
> Task :feature-training:preBuild UP-TO-DATE
> Task :feature-training:preDebugBuild UP-TO-DATE
> Task :feature-training:writeDebugAarMetadata UP-TO-DATE
> Task :app:checkDebugAarMetadata UP-TO-DATE
> Task :app:generateDebugResValues UP-TO-DATE
> Task :core-database:generateDebugResValues UP-TO-DATE
> Task :core-database:generateDebugResources UP-TO-DATE
> Task :core-database:packageDebugResources UP-TO-DATE
> Task :core-mapping:generateDebugResValues UP-TO-DATE
> Task :core-mapping:generateDebugResources UP-TO-DATE
> Task :core-mapping:packageDebugResources UP-TO-DATE
> Task :core-navigation:generateDebugResValues UP-TO-DATE
> Task :core-navigation:generateDebugResources UP-TO-DATE
> Task :core-navigation:packageDebugResources UP-TO-DATE
> Task :core-security:generateDebugResValues UP-TO-DATE
> Task :core-security:generateDebugResources UP-TO-DATE
> Task :core-security:packageDebugResources UP-TO-DATE
> Task :core-storage:generateDebugResValues UP-TO-DATE
> Task :core-storage:generateDebugResources UP-TO-DATE
> Task :core-storage:packageDebugResources UP-TO-DATE
> Task :feature-map:generateDebugResValues UP-TO-DATE
> Task :feature-map:generateDebugResources UP-TO-DATE
> Task :feature-map:packageDebugResources UP-TO-DATE
> Task :feature-profile:generateDebugResValues UP-TO-DATE
> Task :feature-profile:generateDebugResources UP-TO-DATE
> Task :feature-profile:packageDebugResources UP-TO-DATE
> Task :feature-qr:generateDebugResValues UP-TO-DATE
> Task :feature-qr:generateDebugResources UP-TO-DATE
> Task :feature-qr:packageDebugResources UP-TO-DATE
> Task :feature-quiz:generateDebugResValues UP-TO-DATE
> Task :feature-quiz:generateDebugResources UP-TO-DATE
> Task :feature-quiz:packageDebugResources UP-TO-DATE
> Task :feature-training:generateDebugResValues UP-TO-DATE
> Task :feature-training:generateDebugResources UP-TO-DATE
> Task :feature-training:packageDebugResources UP-TO-DATE
> Task :app:mapDebugSourceSetPaths UP-TO-DATE
> Task :app:generateDebugResources UP-TO-DATE
> Task :app:mergeDebugResources UP-TO-DATE
> Task :app:packageDebugResources UP-TO-DATE
> Task :app:parseDebugLocalResources UP-TO-DATE
> Task :app:createDebugCompatibleScreenManifests UP-TO-DATE
> Task :app:extractDeepLinksDebug UP-TO-DATE
> Task :core-database:extractDeepLinksDebug UP-TO-DATE
> Task :core-database:processDebugManifest UP-TO-DATE
> Task :core-mapping:extractDeepLinksDebug UP-TO-DATE
> Task :core-mapping:processDebugManifest UP-TO-DATE
> Task :core-navigation:extractDeepLinksDebug UP-TO-DATE
> Task :core-navigation:processDebugManifest UP-TO-DATE
> Task :core-security:extractDeepLinksDebug UP-TO-DATE
> Task :core-security:processDebugManifest UP-TO-DATE
> Task :core-storage:extractDeepLinksDebug UP-TO-DATE
> Task :core-storage:processDebugManifest UP-TO-DATE
> Task :feature-map:extractDeepLinksDebug UP-TO-DATE
> Task :feature-map:processDebugManifest UP-TO-DATE
> Task :feature-profile:extractDeepLinksDebug UP-TO-DATE
> Task :feature-profile:processDebugManifest UP-TO-DATE
> Task :feature-qr:extractDeepLinksDebug UP-TO-DATE
> Task :feature-qr:processDebugManifest UP-TO-DATE
> Task :feature-quiz:extractDeepLinksDebug UP-TO-DATE
> Task :feature-quiz:processDebugManifest UP-TO-DATE
> Task :feature-training:extractDeepLinksDebug UP-TO-DATE
> Task :feature-training:processDebugManifest UP-TO-DATE
> Task :app:processDebugMainManifest UP-TO-DATE
> Task :app:processDebugManifest UP-TO-DATE
> Task :app:processDebugManifestForPackage UP-TO-DATE
> Task :core-database:compileDebugLibraryResources UP-TO-DATE
> Task :core-database:parseDebugLocalResources UP-TO-DATE
> Task :core-database:generateDebugRFile UP-TO-DATE
> Task :core-mapping:compileDebugLibraryResources UP-TO-DATE
> Task :core-mapping:parseDebugLocalResources UP-TO-DATE
> Task :core-mapping:generateDebugRFile UP-TO-DATE
> Task :core-navigation:compileDebugLibraryResources UP-TO-DATE
> Task :core-navigation:parseDebugLocalResources UP-TO-DATE
> Task :core-navigation:generateDebugRFile UP-TO-DATE
> Task :core-security:compileDebugLibraryResources UP-TO-DATE
> Task :core-security:parseDebugLocalResources UP-TO-DATE
> Task :core-security:generateDebugRFile UP-TO-DATE
> Task :core-storage:compileDebugLibraryResources UP-TO-DATE
> Task :core-storage:parseDebugLocalResources UP-TO-DATE
> Task :core-storage:generateDebugRFile UP-TO-DATE
> Task :feature-map:compileDebugLibraryResources UP-TO-DATE
> Task :feature-map:parseDebugLocalResources UP-TO-DATE
> Task :feature-map:generateDebugRFile UP-TO-DATE
> Task :feature-profile:compileDebugLibraryResources UP-TO-DATE
> Task :feature-profile:parseDebugLocalResources UP-TO-DATE
> Task :feature-profile:generateDebugRFile UP-TO-DATE
> Task :feature-qr:compileDebugLibraryResources UP-TO-DATE
> Task :feature-qr:parseDebugLocalResources UP-TO-DATE
> Task :feature-qr:generateDebugRFile UP-TO-DATE
> Task :feature-quiz:compileDebugLibraryResources UP-TO-DATE
> Task :feature-quiz:parseDebugLocalResources UP-TO-DATE
> Task :feature-quiz:generateDebugRFile UP-TO-DATE
> Task :feature-training:compileDebugLibraryResources UP-TO-DATE
> Task :feature-training:parseDebugLocalResources UP-TO-DATE
> Task :feature-training:generateDebugRFile UP-TO-DATE
> Task :app:processDebugResources UP-TO-DATE
> Task :core-database:checkKotlinGradlePluginConfigurationErrors SKIPPED
> Task :core-database:kspDebugKotlin UP-TO-DATE
> Task :core-database:kaptGenerateStubsDebugKotlin UP-TO-DATE
> Task :core-database:kaptDebugKotlin UP-TO-DATE
> Task :core-database:compileDebugKotlin UP-TO-DATE
> Task :core-database:javaPreCompileDebug UP-TO-DATE
> Task :core-database:compileDebugJavaWithJavac UP-TO-DATE
> Task :core-database:transformDebugClassesWithAsm UP-TO-DATE
> Task :core-database:bundleLibCompileToJarDebug UP-TO-DATE
> Task :core-mapping:checkKotlinGradlePluginConfigurationErrors SKIPPED
> Task :core-mapping:kaptGenerateStubsDebugKotlin UP-TO-DATE
> Task :core-mapping:kaptDebugKotlin UP-TO-DATE
> Task :core-mapping:compileDebugKotlin NO-SOURCE
> Task :core-mapping:javaPreCompileDebug UP-TO-DATE
> Task :core-mapping:compileDebugJavaWithJavac NO-SOURCE
> Task :core-mapping:transformDebugClassesWithAsm UP-TO-DATE
> Task :core-mapping:bundleLibCompileToJarDebug UP-TO-DATE
> Task :core-navigation:checkKotlinGradlePluginConfigurationErrors SKIPPED
> Task :core-navigation:kaptGenerateStubsDebugKotlin UP-TO-DATE
> Task :core-navigation:kaptDebugKotlin UP-TO-DATE
> Task :core-navigation:compileDebugKotlin UP-TO-DATE
> Task :core-navigation:javaPreCompileDebug UP-TO-DATE
> Task :core-navigation:compileDebugJavaWithJavac NO-SOURCE
> Task :core-navigation:transformDebugClassesWithAsm UP-TO-DATE
> Task :core-navigation:bundleLibCompileToJarDebug UP-TO-DATE
> Task :core-security:checkKotlinGradlePluginConfigurationErrors SKIPPED
> Task :core-security:kaptGenerateStubsDebugKotlin UP-TO-DATE
> Task :core-security:kaptDebugKotlin UP-TO-DATE
> Task :core-security:compileDebugKotlin UP-TO-DATE
> Task :core-security:javaPreCompileDebug UP-TO-DATE
> Task :core-security:compileDebugJavaWithJavac NO-SOURCE
> Task :core-security:transformDebugClassesWithAsm UP-TO-DATE
> Task :core-security:bundleLibCompileToJarDebug UP-TO-DATE
> Task :core-storage:checkKotlinGradlePluginConfigurationErrors SKIPPED
> Task :core-storage:kaptGenerateStubsDebugKotlin UP-TO-DATE
> Task :core-storage:kaptDebugKotlin UP-TO-DATE
> Task :core-storage:compileDebugKotlin UP-TO-DATE
> Task :core-storage:javaPreCompileDebug UP-TO-DATE
> Task :core-storage:compileDebugJavaWithJavac NO-SOURCE
> Task :core-storage:transformDebugClassesWithAsm UP-TO-DATE
> Task :core-storage:bundleLibCompileToJarDebug UP-TO-DATE
> Task :core-database:bundleLibRuntimeToJarDebug UP-TO-DATE
> Task :feature-map:checkKotlinGradlePluginConfigurationErrors SKIPPED
> Task :feature-map:kaptGenerateStubsDebugKotlin UP-TO-DATE
> Task :feature-map:kaptDebugKotlin UP-TO-DATE
> Task :feature-map:compileDebugKotlin UP-TO-DATE
> Task :feature-map:javaPreCompileDebug UP-TO-DATE
> Task :feature-map:compileDebugJavaWithJavac UP-TO-DATE
> Task :feature-map:transformDebugClassesWithAsm UP-TO-DATE
> Task :feature-map:bundleLibCompileToJarDebug UP-TO-DATE
> Task :feature-profile:checkKotlinGradlePluginConfigurationErrors SKIPPED
> Task :feature-profile:kaptGenerateStubsDebugKotlin UP-TO-DATE
> Task :feature-profile:kaptDebugKotlin UP-TO-DATE
> Task :feature-profile:compileDebugKotlin UP-TO-DATE
> Task :feature-profile:javaPreCompileDebug UP-TO-DATE
> Task :feature-profile:compileDebugJavaWithJavac UP-TO-DATE
> Task :feature-profile:transformDebugClassesWithAsm UP-TO-DATE
> Task :feature-profile:bundleLibCompileToJarDebug UP-TO-DATE
> Task :feature-qr:checkKotlinGradlePluginConfigurationErrors SKIPPED
> Task :feature-qr:kaptGenerateStubsDebugKotlin UP-TO-DATE
> Task :feature-qr:kaptDebugKotlin UP-TO-DATE
> Task :feature-qr:compileDebugKotlin UP-TO-DATE
> Task :feature-qr:javaPreCompileDebug UP-TO-DATE
> Task :feature-qr:compileDebugJavaWithJavac UP-TO-DATE
> Task :feature-qr:transformDebugClassesWithAsm UP-TO-DATE
> Task :feature-qr:bundleLibCompileToJarDebug UP-TO-DATE
> Task :feature-quiz:checkKotlinGradlePluginConfigurationErrors SKIPPED
> Task :feature-quiz:kaptGenerateStubsDebugKotlin UP-TO-DATE
> Task :feature-quiz:kaptDebugKotlin UP-TO-DATE
> Task :feature-quiz:compileDebugKotlin UP-TO-DATE
> Task :feature-quiz:javaPreCompileDebug UP-TO-DATE
> Task :feature-quiz:compileDebugJavaWithJavac UP-TO-DATE
> Task :feature-quiz:transformDebugClassesWithAsm UP-TO-DATE
> Task :feature-quiz:bundleLibCompileToJarDebug UP-TO-DATE
> Task :feature-training:checkKotlinGradlePluginConfigurationErrors SKIPPED
> Task :feature-training:kaptGenerateStubsDebugKotlin UP-TO-DATE
> Task :feature-training:kaptDebugKotlin UP-TO-DATE
> Task :feature-training:compileDebugKotlin UP-TO-DATE
> Task :feature-training:javaPreCompileDebug UP-TO-DATE
> Task :feature-training:compileDebugJavaWithJavac UP-TO-DATE
> Task :feature-training:transformDebugClassesWithAsm UP-TO-DATE
> Task :feature-training:bundleLibCompileToJarDebug UP-TO-DATE
> Task :app:kaptGenerateStubsDebugKotlin UP-TO-DATE
> Task :app:kaptDebugKotlin UP-TO-DATE
> Task :app:compileDebugKotlin UP-TO-DATE
> Task :app:javaPreCompileDebug UP-TO-DATE
> Task :app:compileDebugJavaWithJavac UP-TO-DATE
> Task :core-navigation:bundleLibRuntimeToJarDebug UP-TO-DATE
> Task :feature-quiz:bundleLibRuntimeToJarDebug UP-TO-DATE
> Task :feature-map:bundleLibRuntimeToJarDebug UP-TO-DATE
> Task :feature-training:bundleLibRuntimeToJarDebug UP-TO-DATE
> Task :feature-qr:bundleLibRuntimeToJarDebug UP-TO-DATE
> Task :feature-profile:bundleLibRuntimeToJarDebug UP-TO-DATE
> Task :core-storage:bundleLibRuntimeToJarDebug UP-TO-DATE
> Task :core-security:bundleLibRuntimeToJarDebug UP-TO-DATE
> Task :core-mapping:bundleLibRuntimeToJarDebug UP-TO-DATE
> Task :app:hiltAggregateDepsDebug UP-TO-DATE
> Task :app:hiltJavaCompileDebug UP-TO-DATE
> Task :app:transformDebugClassesWithAsm UP-TO-DATE
> Task :app:bundleDebugClassesToRuntimeJar UP-TO-DATE
> Task :app:preDebugUnitTestBuild UP-TO-DATE
> Task :app:processDebugJavaRes UP-TO-DATE
> Task :app:bundleDebugClassesToCompileJar UP-TO-DATE
> Task :app:kaptGenerateStubsDebugUnitTestKotlin UP-TO-DATE
> Task :app:kaptDebugUnitTestKotlin UP-TO-DATE
> Task :app:compileDebugUnitTestKotlin NO-SOURCE
> Task :app:javaPreCompileDebugUnitTest UP-TO-DATE
> Task :app:compileDebugUnitTestJavaWithJavac NO-SOURCE
> Task :app:hiltAggregateDepsDebugUnitTest UP-TO-DATE
> Task :app:hiltJavaCompileDebugUnitTest NO-SOURCE
> Task :app:processDebugUnitTestJavaRes NO-SOURCE
> Task :app:transformDebugUnitTestClassesWithAsm UP-TO-DATE
> Task :core-database:processDebugJavaRes UP-TO-DATE
> Task :core-mapping:processDebugJavaRes NO-SOURCE
> Task :core-navigation:processDebugJavaRes UP-TO-DATE
> Task :core-security:processDebugJavaRes UP-TO-DATE
> Task :core-storage:processDebugJavaRes UP-TO-DATE
> Task :feature-map:processDebugJavaRes UP-TO-DATE
> Task :feature-profile:processDebugJavaRes UP-TO-DATE
> Task :feature-qr:processDebugJavaRes UP-TO-DATE
> Task :feature-quiz:processDebugJavaRes UP-TO-DATE
> Task :feature-training:processDebugJavaRes UP-TO-DATE
> Task :app:testDebugUnitTest NO-SOURCE
> Task :app:preReleaseBuild UP-TO-DATE
> Task :app:buildKotlinToolingMetadata UP-TO-DATE
> Task :core-database:preReleaseBuild UP-TO-DATE
> Task :core-database:writeReleaseAarMetadata UP-TO-DATE
> Task :core-mapping:preReleaseBuild UP-TO-DATE
> Task :core-mapping:writeReleaseAarMetadata UP-TO-DATE
> Task :core-navigation:preReleaseBuild UP-TO-DATE
> Task :core-navigation:writeReleaseAarMetadata UP-TO-DATE
> Task :core-security:preReleaseBuild UP-TO-DATE
> Task :core-security:writeReleaseAarMetadata UP-TO-DATE
> Task :core-storage:preReleaseBuild UP-TO-DATE
> Task :core-storage:writeReleaseAarMetadata UP-TO-DATE
> Task :feature-map:preReleaseBuild UP-TO-DATE
> Task :feature-map:writeReleaseAarMetadata UP-TO-DATE
> Task :feature-profile:preReleaseBuild UP-TO-DATE
> Task :feature-profile:writeReleaseAarMetadata UP-TO-DATE
> Task :feature-qr:preReleaseBuild UP-TO-DATE
> Task :feature-qr:writeReleaseAarMetadata UP-TO-DATE
> Task :feature-quiz:preReleaseBuild UP-TO-DATE
> Task :feature-quiz:writeReleaseAarMetadata UP-TO-DATE
> Task :feature-training:preReleaseBuild UP-TO-DATE
> Task :feature-training:writeReleaseAarMetadata UP-TO-DATE
> Task :app:checkReleaseAarMetadata UP-TO-DATE
> Task :app:generateReleaseResValues UP-TO-DATE
> Task :core-database:generateReleaseResValues UP-TO-DATE
> Task :core-database:generateReleaseResources UP-TO-DATE
> Task :core-database:packageReleaseResources UP-TO-DATE
> Task :core-mapping:generateReleaseResValues UP-TO-DATE
> Task :core-mapping:generateReleaseResources UP-TO-DATE
> Task :core-mapping:packageReleaseResources UP-TO-DATE
> Task :core-navigation:generateReleaseResValues UP-TO-DATE
> Task :core-navigation:generateReleaseResources UP-TO-DATE
> Task :core-navigation:packageReleaseResources UP-TO-DATE
> Task :core-security:generateReleaseResValues UP-TO-DATE
> Task :core-security:generateReleaseResources UP-TO-DATE
> Task :core-security:packageReleaseResources UP-TO-DATE
> Task :core-storage:generateReleaseResValues UP-TO-DATE
> Task :core-storage:generateReleaseResources UP-TO-DATE
> Task :core-storage:packageReleaseResources UP-TO-DATE
> Task :feature-map:generateReleaseResValues UP-TO-DATE
> Task :feature-map:generateReleaseResources UP-TO-DATE
> Task :feature-map:packageReleaseResources UP-TO-DATE
> Task :feature-profile:generateReleaseResValues UP-TO-DATE
> Task :feature-profile:generateReleaseResources UP-TO-DATE
> Task :feature-profile:packageReleaseResources UP-TO-DATE
> Task :feature-qr:generateReleaseResValues UP-TO-DATE
> Task :feature-qr:generateReleaseResources UP-TO-DATE
> Task :feature-qr:packageReleaseResources UP-TO-DATE
> Task :feature-quiz:generateReleaseResValues UP-TO-DATE
> Task :feature-quiz:generateReleaseResources UP-TO-DATE
> Task :feature-quiz:packageReleaseResources UP-TO-DATE
> Task :feature-training:generateReleaseResValues UP-TO-DATE
> Task :feature-training:generateReleaseResources UP-TO-DATE
> Task :feature-training:packageReleaseResources UP-TO-DATE
> Task :app:mapReleaseSourceSetPaths UP-TO-DATE
> Task :app:generateReleaseResources UP-TO-DATE
> Task :app:mergeReleaseResources UP-TO-DATE
> Task :app:packageReleaseResources UP-TO-DATE
> Task :app:parseReleaseLocalResources UP-TO-DATE
> Task :app:createReleaseCompatibleScreenManifests UP-TO-DATE
> Task :app:extractDeepLinksRelease UP-TO-DATE
> Task :core-database:extractDeepLinksRelease UP-TO-DATE
> Task :core-database:processReleaseManifest UP-TO-DATE
> Task :core-mapping:extractDeepLinksRelease UP-TO-DATE
> Task :core-mapping:processReleaseManifest UP-TO-DATE
> Task :core-navigation:extractDeepLinksRelease UP-TO-DATE
> Task :core-navigation:processReleaseManifest UP-TO-DATE
> Task :core-security:extractDeepLinksRelease UP-TO-DATE
> Task :core-security:processReleaseManifest UP-TO-DATE
> Task :core-storage:extractDeepLinksRelease UP-TO-DATE
> Task :core-storage:processReleaseManifest UP-TO-DATE
> Task :feature-map:extractDeepLinksRelease UP-TO-DATE
> Task :feature-map:processReleaseManifest UP-TO-DATE
> Task :feature-profile:extractDeepLinksRelease UP-TO-DATE
> Task :feature-profile:processReleaseManifest UP-TO-DATE
> Task :feature-qr:extractDeepLinksRelease UP-TO-DATE
> Task :feature-qr:processReleaseManifest UP-TO-DATE
> Task :feature-quiz:extractDeepLinksRelease UP-TO-DATE
> Task :feature-quiz:processReleaseManifest UP-TO-DATE
> Task :feature-training:extractDeepLinksRelease UP-TO-DATE
> Task :feature-training:processReleaseManifest UP-TO-DATE
> Task :app:processReleaseMainManifest UP-TO-DATE
> Task :app:processReleaseManifest UP-TO-DATE
> Task :app:processReleaseManifestForPackage UP-TO-DATE
> Task :core-database:compileReleaseLibraryResources UP-TO-DATE
> Task :core-database:parseReleaseLocalResources UP-TO-DATE
> Task :core-database:generateReleaseRFile UP-TO-DATE
> Task :core-mapping:compileReleaseLibraryResources UP-TO-DATE
> Task :core-mapping:parseReleaseLocalResources UP-TO-DATE
> Task :core-mapping:generateReleaseRFile UP-TO-DATE
> Task :core-navigation:compileReleaseLibraryResources UP-TO-DATE
> Task :core-navigation:parseReleaseLocalResources UP-TO-DATE
> Task :core-navigation:generateReleaseRFile UP-TO-DATE
> Task :core-security:compileReleaseLibraryResources UP-TO-DATE
> Task :core-security:parseReleaseLocalResources UP-TO-DATE
> Task :core-security:generateReleaseRFile UP-TO-DATE
> Task :core-storage:compileReleaseLibraryResources UP-TO-DATE
> Task :core-storage:parseReleaseLocalResources UP-TO-DATE
> Task :core-storage:generateReleaseRFile UP-TO-DATE
> Task :feature-map:compileReleaseLibraryResources UP-TO-DATE
> Task :feature-map:parseReleaseLocalResources UP-TO-DATE
> Task :feature-map:generateReleaseRFile UP-TO-DATE
> Task :feature-profile:compileReleaseLibraryResources UP-TO-DATE
> Task :feature-profile:parseReleaseLocalResources UP-TO-DATE
> Task :feature-profile:generateReleaseRFile UP-TO-DATE
> Task :feature-qr:compileReleaseLibraryResources UP-TO-DATE
> Task :feature-qr:parseReleaseLocalResources UP-TO-DATE
> Task :feature-qr:generateReleaseRFile UP-TO-DATE
> Task :feature-quiz:compileReleaseLibraryResources UP-TO-DATE
> Task :feature-quiz:parseReleaseLocalResources UP-TO-DATE
> Task :feature-quiz:generateReleaseRFile UP-TO-DATE
> Task :feature-training:compileReleaseLibraryResources UP-TO-DATE
> Task :feature-training:parseReleaseLocalResources UP-TO-DATE
> Task :feature-training:generateReleaseRFile UP-TO-DATE
> Task :app:processReleaseResources UP-TO-DATE
> Task :core-database:kspReleaseKotlin UP-TO-DATE
> Task :core-database:kaptGenerateStubsReleaseKotlin UP-TO-DATE
> Task :core-database:kaptReleaseKotlin UP-TO-DATE
> Task :core-database:compileReleaseKotlin UP-TO-DATE
> Task :core-database:javaPreCompileRelease UP-TO-DATE
> Task :core-database:compileReleaseJavaWithJavac UP-TO-DATE
> Task :core-database:transformReleaseClassesWithAsm UP-TO-DATE
> Task :core-database:bundleLibCompileToJarRelease UP-TO-DATE
> Task :core-mapping:kaptGenerateStubsReleaseKotlin UP-TO-DATE
> Task :core-mapping:kaptReleaseKotlin UP-TO-DATE
> Task :core-mapping:compileReleaseKotlin NO-SOURCE
> Task :core-mapping:javaPreCompileRelease UP-TO-DATE
> Task :core-mapping:compileReleaseJavaWithJavac NO-SOURCE
> Task :core-mapping:transformReleaseClassesWithAsm UP-TO-DATE
> Task :core-mapping:bundleLibCompileToJarRelease UP-TO-DATE
> Task :core-navigation:kaptGenerateStubsReleaseKotlin UP-TO-DATE
> Task :core-navigation:kaptReleaseKotlin UP-TO-DATE
> Task :core-navigation:compileReleaseKotlin UP-TO-DATE
> Task :core-navigation:javaPreCompileRelease UP-TO-DATE
> Task :core-navigation:compileReleaseJavaWithJavac NO-SOURCE
> Task :core-navigation:transformReleaseClassesWithAsm UP-TO-DATE
> Task :core-navigation:bundleLibCompileToJarRelease UP-TO-DATE
> Task :core-security:kaptGenerateStubsReleaseKotlin UP-TO-DATE
> Task :core-security:kaptReleaseKotlin UP-TO-DATE
> Task :core-security:compileReleaseKotlin UP-TO-DATE
> Task :core-security:javaPreCompileRelease UP-TO-DATE
> Task :core-security:compileReleaseJavaWithJavac NO-SOURCE
> Task :core-security:transformReleaseClassesWithAsm UP-TO-DATE
> Task :core-security:bundleLibCompileToJarRelease UP-TO-DATE
> Task :core-storage:kaptGenerateStubsReleaseKotlin UP-TO-DATE
> Task :core-storage:kaptReleaseKotlin UP-TO-DATE
> Task :core-storage:compileReleaseKotlin UP-TO-DATE
> Task :core-storage:javaPreCompileRelease UP-TO-DATE
> Task :core-storage:compileReleaseJavaWithJavac NO-SOURCE
> Task :core-storage:transformReleaseClassesWithAsm UP-TO-DATE
> Task :core-storage:bundleLibCompileToJarRelease UP-TO-DATE
> Task :core-database:bundleLibRuntimeToJarRelease UP-TO-DATE
> Task :feature-map:kaptGenerateStubsReleaseKotlin UP-TO-DATE
> Task :feature-map:kaptReleaseKotlin UP-TO-DATE
> Task :feature-map:compileReleaseKotlin UP-TO-DATE
> Task :feature-map:javaPreCompileRelease UP-TO-DATE
> Task :feature-map:compileReleaseJavaWithJavac UP-TO-DATE
> Task :feature-map:transformReleaseClassesWithAsm UP-TO-DATE
> Task :feature-map:bundleLibCompileToJarRelease UP-TO-DATE
> Task :feature-profile:kaptGenerateStubsReleaseKotlin UP-TO-DATE
> Task :feature-profile:kaptReleaseKotlin UP-TO-DATE
> Task :feature-profile:compileReleaseKotlin UP-TO-DATE
> Task :feature-profile:javaPreCompileRelease UP-TO-DATE
> Task :feature-profile:compileReleaseJavaWithJavac UP-TO-DATE
> Task :feature-profile:transformReleaseClassesWithAsm UP-TO-DATE
> Task :feature-profile:bundleLibCompileToJarRelease UP-TO-DATE
> Task :feature-qr:kaptGenerateStubsReleaseKotlin UP-TO-DATE
> Task :feature-qr:kaptReleaseKotlin UP-TO-DATE
> Task :feature-qr:compileReleaseKotlin UP-TO-DATE
> Task :feature-qr:javaPreCompileRelease UP-TO-DATE
> Task :feature-qr:compileReleaseJavaWithJavac UP-TO-DATE
> Task :feature-qr:transformReleaseClassesWithAsm UP-TO-DATE
> Task :feature-qr:bundleLibCompileToJarRelease UP-TO-DATE
> Task :feature-quiz:kaptGenerateStubsReleaseKotlin UP-TO-DATE
> Task :feature-quiz:kaptReleaseKotlin UP-TO-DATE
> Task :feature-quiz:compileReleaseKotlin UP-TO-DATE
> Task :feature-quiz:javaPreCompileRelease UP-TO-DATE
> Task :feature-quiz:compileReleaseJavaWithJavac UP-TO-DATE
> Task :feature-quiz:transformReleaseClassesWithAsm UP-TO-DATE
> Task :feature-quiz:bundleLibCompileToJarRelease UP-TO-DATE
> Task :feature-training:kaptGenerateStubsReleaseKotlin UP-TO-DATE
> Task :feature-training:kaptReleaseKotlin UP-TO-DATE
> Task :feature-training:compileReleaseKotlin UP-TO-DATE
> Task :feature-training:javaPreCompileRelease UP-TO-DATE
> Task :feature-training:compileReleaseJavaWithJavac UP-TO-DATE
> Task :feature-training:transformReleaseClassesWithAsm UP-TO-DATE
> Task :feature-training:bundleLibCompileToJarRelease UP-TO-DATE
> Task :app:kaptGenerateStubsReleaseKotlin UP-TO-DATE
> Task :app:kaptReleaseKotlin UP-TO-DATE
> Task :app:compileReleaseKotlin UP-TO-DATE
> Task :app:javaPreCompileRelease UP-TO-DATE
> Task :app:compileReleaseJavaWithJavac UP-TO-DATE
> Task :core-navigation:bundleLibRuntimeToJarRelease UP-TO-DATE
> Task :feature-quiz:bundleLibRuntimeToJarRelease UP-TO-DATE
> Task :feature-map:bundleLibRuntimeToJarRelease UP-TO-DATE
> Task :feature-training:bundleLibRuntimeToJarRelease UP-TO-DATE
> Task :feature-qr:bundleLibRuntimeToJarRelease UP-TO-DATE
> Task :feature-profile:bundleLibRuntimeToJarRelease UP-TO-DATE
> Task :core-storage:bundleLibRuntimeToJarRelease UP-TO-DATE
> Task :core-security:bundleLibRuntimeToJarRelease UP-TO-DATE
> Task :core-mapping:bundleLibRuntimeToJarRelease UP-TO-DATE
> Task :app:preReleaseUnitTestBuild UP-TO-DATE
> Task :app:hiltAggregateDepsRelease UP-TO-DATE
> Task :app:hiltJavaCompileRelease UP-TO-DATE
> Task :app:transformReleaseClassesWithAsm UP-TO-DATE
> Task :app:bundleReleaseClassesToRuntimeJar UP-TO-DATE
> Task :app:processReleaseJavaRes UP-TO-DATE
> Task :app:bundleReleaseClassesToCompileJar UP-TO-DATE
> Task :app:kaptGenerateStubsReleaseUnitTestKotlin UP-TO-DATE
> Task :app:kaptReleaseUnitTestKotlin UP-TO-DATE
> Task :app:compileReleaseUnitTestKotlin NO-SOURCE
> Task :app:javaPreCompileReleaseUnitTest UP-TO-DATE
> Task :app:compileReleaseUnitTestJavaWithJavac NO-SOURCE
> Task :app:hiltAggregateDepsReleaseUnitTest UP-TO-DATE
> Task :app:hiltJavaCompileReleaseUnitTest NO-SOURCE
> Task :app:processReleaseUnitTestJavaRes NO-SOURCE
> Task :app:transformReleaseUnitTestClassesWithAsm UP-TO-DATE
> Task :core-database:processReleaseJavaRes UP-TO-DATE
> Task :core-mapping:processReleaseJavaRes NO-SOURCE
> Task :core-navigation:processReleaseJavaRes UP-TO-DATE
> Task :core-security:processReleaseJavaRes UP-TO-DATE
> Task :core-storage:processReleaseJavaRes UP-TO-DATE
> Task :feature-map:processReleaseJavaRes UP-TO-DATE
> Task :feature-profile:processReleaseJavaRes UP-TO-DATE
> Task :feature-qr:processReleaseJavaRes UP-TO-DATE
> Task :feature-quiz:processReleaseJavaRes UP-TO-DATE
> Task :feature-training:processReleaseJavaRes UP-TO-DATE
> Task :app:testReleaseUnitTest NO-SOURCE
> Task :app:test UP-TO-DATE
> Task :core-database:preDebugUnitTestBuild UP-TO-DATE
> Task :core-database:generateDebugUnitTestStubRFile UP-TO-DATE
> Task :core-database:kspDebugUnitTestKotlin NO-SOURCE
> Task :core-database:kaptGenerateStubsDebugUnitTestKotlin UP-TO-DATE
> Task :core-database:kaptDebugUnitTestKotlin UP-TO-DATE
> Task :core-database:compileDebugUnitTestKotlin NO-SOURCE
> Task :core-database:javaPreCompileDebugUnitTest UP-TO-DATE
> Task :core-database:compileDebugUnitTestJavaWithJavac NO-SOURCE
> Task :core-database:hiltAggregateDepsDebugUnitTest UP-TO-DATE
> Task :core-database:hiltJavaCompileDebugUnitTest NO-SOURCE
> Task :core-database:processDebugUnitTestJavaRes NO-SOURCE
> Task :core-database:transformDebugUnitTestClassesWithAsm UP-TO-DATE
> Task :core-database:testDebugUnitTest NO-SOURCE
> Task :core-database:preReleaseUnitTestBuild UP-TO-DATE
> Task :core-database:generateReleaseUnitTestStubRFile UP-TO-DATE
> Task :core-database:kspReleaseUnitTestKotlin NO-SOURCE
> Task :core-database:kaptGenerateStubsReleaseUnitTestKotlin UP-TO-DATE
> Task :core-database:kaptReleaseUnitTestKotlin UP-TO-DATE
> Task :core-database:compileReleaseUnitTestKotlin NO-SOURCE
> Task :core-database:javaPreCompileReleaseUnitTest UP-TO-DATE
> Task :core-database:compileReleaseUnitTestJavaWithJavac NO-SOURCE
> Task :core-database:hiltAggregateDepsReleaseUnitTest UP-TO-DATE
> Task :core-database:hiltJavaCompileReleaseUnitTest NO-SOURCE
> Task :core-database:processReleaseUnitTestJavaRes NO-SOURCE
> Task :core-database:transformReleaseUnitTestClassesWithAsm UP-TO-DATE
> Task :core-database:testReleaseUnitTest NO-SOURCE
> Task :core-database:test UP-TO-DATE
> Task :core-mapping:preDebugUnitTestBuild UP-TO-DATE
> Task :core-mapping:generateDebugUnitTestStubRFile UP-TO-DATE
> Task :core-mapping:kaptGenerateStubsDebugUnitTestKotlin UP-TO-DATE
> Task :core-mapping:kaptDebugUnitTestKotlin UP-TO-DATE
> Task :core-mapping:compileDebugUnitTestKotlin NO-SOURCE
> Task :core-mapping:javaPreCompileDebugUnitTest UP-TO-DATE
> Task :core-mapping:compileDebugUnitTestJavaWithJavac NO-SOURCE
> Task :core-mapping:hiltAggregateDepsDebugUnitTest UP-TO-DATE
> Task :core-mapping:hiltJavaCompileDebugUnitTest NO-SOURCE
> Task :core-mapping:processDebugUnitTestJavaRes NO-SOURCE
> Task :core-mapping:transformDebugUnitTestClassesWithAsm UP-TO-DATE
> Task :core-mapping:testDebugUnitTest NO-SOURCE
> Task :core-mapping:preReleaseUnitTestBuild UP-TO-DATE
> Task :core-mapping:generateReleaseUnitTestStubRFile UP-TO-DATE
> Task :core-mapping:kaptGenerateStubsReleaseUnitTestKotlin UP-TO-DATE
> Task :core-mapping:kaptReleaseUnitTestKotlin UP-TO-DATE
> Task :core-mapping:compileReleaseUnitTestKotlin NO-SOURCE
> Task :core-mapping:javaPreCompileReleaseUnitTest UP-TO-DATE
> Task :core-mapping:compileReleaseUnitTestJavaWithJavac NO-SOURCE
> Task :core-mapping:hiltAggregateDepsReleaseUnitTest UP-TO-DATE
> Task :core-mapping:hiltJavaCompileReleaseUnitTest NO-SOURCE
> Task :core-mapping:processReleaseUnitTestJavaRes NO-SOURCE
> Task :core-mapping:transformReleaseUnitTestClassesWithAsm UP-TO-DATE
> Task :core-mapping:testReleaseUnitTest NO-SOURCE
> Task :core-mapping:test UP-TO-DATE
> Task :core-navigation:preDebugUnitTestBuild UP-TO-DATE
> Task :core-navigation:generateDebugUnitTestStubRFile UP-TO-DATE
> Task :core-navigation:kaptGenerateStubsDebugUnitTestKotlin UP-TO-DATE
> Task :core-navigation:kaptDebugUnitTestKotlin UP-TO-DATE
> Task :core-navigation:compileDebugUnitTestKotlin NO-SOURCE
> Task :core-navigation:javaPreCompileDebugUnitTest UP-TO-DATE
> Task :core-navigation:compileDebugUnitTestJavaWithJavac NO-SOURCE
> Task :core-navigation:hiltAggregateDepsDebugUnitTest UP-TO-DATE
> Task :core-navigation:hiltJavaCompileDebugUnitTest NO-SOURCE
> Task :core-navigation:processDebugUnitTestJavaRes NO-SOURCE
> Task :core-navigation:transformDebugUnitTestClassesWithAsm UP-TO-DATE
> Task :core-navigation:testDebugUnitTest NO-SOURCE
> Task :core-navigation:preReleaseUnitTestBuild UP-TO-DATE
> Task :core-navigation:generateReleaseUnitTestStubRFile UP-TO-DATE
> Task :core-navigation:kaptGenerateStubsReleaseUnitTestKotlin UP-TO-DATE
> Task :core-navigation:kaptReleaseUnitTestKotlin UP-TO-DATE
> Task :core-navigation:compileReleaseUnitTestKotlin NO-SOURCE
> Task :core-navigation:javaPreCompileReleaseUnitTest UP-TO-DATE
> Task :core-navigation:compileReleaseUnitTestJavaWithJavac NO-SOURCE
> Task :core-navigation:hiltAggregateDepsReleaseUnitTest UP-TO-DATE
> Task :core-navigation:hiltJavaCompileReleaseUnitTest NO-SOURCE
> Task :core-navigation:processReleaseUnitTestJavaRes NO-SOURCE
> Task :core-navigation:transformReleaseUnitTestClassesWithAsm UP-TO-DATE
> Task :core-navigation:testReleaseUnitTest NO-SOURCE
> Task :core-navigation:test UP-TO-DATE
> Task :core-security:preDebugUnitTestBuild UP-TO-DATE
> Task :core-security:generateDebugUnitTestStubRFile UP-TO-DATE
> Task :core-security:kaptGenerateStubsDebugUnitTestKotlin UP-TO-DATE
> Task :core-security:kaptDebugUnitTestKotlin UP-TO-DATE
> Task :core-security:compileDebugUnitTestKotlin NO-SOURCE
> Task :core-security:javaPreCompileDebugUnitTest UP-TO-DATE
> Task :core-security:compileDebugUnitTestJavaWithJavac NO-SOURCE
> Task :core-security:hiltAggregateDepsDebugUnitTest UP-TO-DATE
> Task :core-security:hiltJavaCompileDebugUnitTest NO-SOURCE
> Task :core-security:processDebugUnitTestJavaRes NO-SOURCE
> Task :core-security:transformDebugUnitTestClassesWithAsm UP-TO-DATE
> Task :core-security:testDebugUnitTest NO-SOURCE
> Task :core-security:preReleaseUnitTestBuild UP-TO-DATE
> Task :core-security:generateReleaseUnitTestStubRFile UP-TO-DATE
> Task :core-security:kaptGenerateStubsReleaseUnitTestKotlin UP-TO-DATE
> Task :core-security:kaptReleaseUnitTestKotlin UP-TO-DATE
> Task :core-security:compileReleaseUnitTestKotlin NO-SOURCE
> Task :core-security:javaPreCompileReleaseUnitTest UP-TO-DATE
> Task :core-security:compileReleaseUnitTestJavaWithJavac NO-SOURCE
> Task :core-security:hiltAggregateDepsReleaseUnitTest UP-TO-DATE
> Task :core-security:hiltJavaCompileReleaseUnitTest NO-SOURCE
> Task :core-security:processReleaseUnitTestJavaRes NO-SOURCE
> Task :core-security:transformReleaseUnitTestClassesWithAsm UP-TO-DATE
> Task :core-security:testReleaseUnitTest NO-SOURCE
> Task :core-security:test UP-TO-DATE
> Task :core-storage:preDebugUnitTestBuild UP-TO-DATE
> Task :core-storage:generateDebugUnitTestStubRFile UP-TO-DATE
> Task :core-storage:kaptGenerateStubsDebugUnitTestKotlin UP-TO-DATE
> Task :core-storage:kaptDebugUnitTestKotlin UP-TO-DATE
> Task :core-storage:compileDebugUnitTestKotlin NO-SOURCE
> Task :core-storage:javaPreCompileDebugUnitTest UP-TO-DATE
> Task :core-storage:compileDebugUnitTestJavaWithJavac NO-SOURCE
> Task :core-storage:hiltAggregateDepsDebugUnitTest UP-TO-DATE
> Task :core-storage:hiltJavaCompileDebugUnitTest NO-SOURCE
> Task :core-storage:processDebugUnitTestJavaRes NO-SOURCE
> Task :core-storage:transformDebugUnitTestClassesWithAsm UP-TO-DATE
> Task :core-storage:testDebugUnitTest NO-SOURCE
> Task :core-storage:preReleaseUnitTestBuild UP-TO-DATE
> Task :core-storage:generateReleaseUnitTestStubRFile UP-TO-DATE
> Task :core-storage:kaptGenerateStubsReleaseUnitTestKotlin UP-TO-DATE
> Task :core-storage:kaptReleaseUnitTestKotlin UP-TO-DATE
> Task :core-storage:compileReleaseUnitTestKotlin NO-SOURCE
> Task :core-storage:javaPreCompileReleaseUnitTest UP-TO-DATE
> Task :core-storage:compileReleaseUnitTestJavaWithJavac NO-SOURCE
> Task :core-storage:hiltAggregateDepsReleaseUnitTest UP-TO-DATE
> Task :core-storage:hiltJavaCompileReleaseUnitTest NO-SOURCE
> Task :core-storage:processReleaseUnitTestJavaRes NO-SOURCE
> Task :core-storage:transformReleaseUnitTestClassesWithAsm UP-TO-DATE
> Task :core-storage:testReleaseUnitTest NO-SOURCE
> Task :core-storage:test UP-TO-DATE
> Task :feature-map:preDebugUnitTestBuild UP-TO-DATE
> Task :feature-map:generateDebugUnitTestStubRFile UP-TO-DATE
> Task :feature-map:kaptGenerateStubsDebugUnitTestKotlin UP-TO-DATE
> Task :feature-map:kaptDebugUnitTestKotlin UP-TO-DATE
> Task :feature-map:compileDebugUnitTestKotlin NO-SOURCE
> Task :feature-map:javaPreCompileDebugUnitTest UP-TO-DATE
> Task :feature-map:compileDebugUnitTestJavaWithJavac NO-SOURCE
> Task :feature-map:hiltAggregateDepsDebugUnitTest UP-TO-DATE
> Task :feature-map:hiltJavaCompileDebugUnitTest NO-SOURCE
> Task :feature-map:processDebugUnitTestJavaRes NO-SOURCE
> Task :feature-map:transformDebugUnitTestClassesWithAsm UP-TO-DATE
> Task :feature-map:testDebugUnitTest NO-SOURCE
> Task :feature-map:preReleaseUnitTestBuild UP-TO-DATE
> Task :feature-map:generateReleaseUnitTestStubRFile UP-TO-DATE
> Task :feature-map:kaptGenerateStubsReleaseUnitTestKotlin UP-TO-DATE
> Task :feature-map:kaptReleaseUnitTestKotlin UP-TO-DATE
> Task :feature-map:compileReleaseUnitTestKotlin NO-SOURCE
> Task :feature-map:javaPreCompileReleaseUnitTest UP-TO-DATE
> Task :feature-map:compileReleaseUnitTestJavaWithJavac NO-SOURCE
> Task :feature-map:hiltAggregateDepsReleaseUnitTest UP-TO-DATE
> Task :feature-map:hiltJavaCompileReleaseUnitTest NO-SOURCE
> Task :feature-map:processReleaseUnitTestJavaRes NO-SOURCE
> Task :feature-map:transformReleaseUnitTestClassesWithAsm UP-TO-DATE
> Task :feature-map:testReleaseUnitTest NO-SOURCE
> Task :feature-map:test UP-TO-DATE
> Task :feature-profile:preDebugUnitTestBuild UP-TO-DATE
> Task :feature-profile:generateDebugUnitTestStubRFile UP-TO-DATE
> Task :feature-profile:kaptGenerateStubsDebugUnitTestKotlin UP-TO-DATE
> Task :feature-profile:kaptDebugUnitTestKotlin UP-TO-DATE
> Task :feature-profile:compileDebugUnitTestKotlin NO-SOURCE
> Task :feature-profile:javaPreCompileDebugUnitTest UP-TO-DATE
> Task :feature-profile:compileDebugUnitTestJavaWithJavac NO-SOURCE
> Task :feature-profile:hiltAggregateDepsDebugUnitTest UP-TO-DATE
> Task :feature-profile:hiltJavaCompileDebugUnitTest NO-SOURCE
> Task :feature-profile:processDebugUnitTestJavaRes NO-SOURCE
> Task :feature-profile:transformDebugUnitTestClassesWithAsm UP-TO-DATE
> Task :feature-profile:testDebugUnitTest NO-SOURCE
> Task :feature-profile:preReleaseUnitTestBuild UP-TO-DATE
> Task :feature-profile:generateReleaseUnitTestStubRFile UP-TO-DATE
> Task :feature-profile:kaptGenerateStubsReleaseUnitTestKotlin UP-TO-DATE
> Task :feature-profile:kaptReleaseUnitTestKotlin UP-TO-DATE
> Task :feature-profile:compileReleaseUnitTestKotlin NO-SOURCE
> Task :feature-profile:javaPreCompileReleaseUnitTest UP-TO-DATE
> Task :feature-profile:compileReleaseUnitTestJavaWithJavac NO-SOURCE
> Task :feature-profile:hiltAggregateDepsReleaseUnitTest UP-TO-DATE
> Task :feature-profile:hiltJavaCompileReleaseUnitTest NO-SOURCE
> Task :feature-profile:processReleaseUnitTestJavaRes NO-SOURCE
> Task :feature-profile:transformReleaseUnitTestClassesWithAsm UP-TO-DATE
> Task :feature-profile:testReleaseUnitTest NO-SOURCE
> Task :feature-profile:test UP-TO-DATE
> Task :feature-qr:preDebugUnitTestBuild UP-TO-DATE
> Task :feature-qr:generateDebugUnitTestStubRFile UP-TO-DATE
> Task :feature-qr:kaptGenerateStubsDebugUnitTestKotlin UP-TO-DATE
> Task :feature-qr:kaptDebugUnitTestKotlin UP-TO-DATE
> Task :feature-qr:compileDebugUnitTestKotlin NO-SOURCE
> Task :feature-qr:javaPreCompileDebugUnitTest UP-TO-DATE
> Task :feature-qr:compileDebugUnitTestJavaWithJavac NO-SOURCE
> Task :feature-qr:hiltAggregateDepsDebugUnitTest UP-TO-DATE
> Task :feature-qr:hiltJavaCompileDebugUnitTest NO-SOURCE
> Task :feature-qr:processDebugUnitTestJavaRes NO-SOURCE
> Task :feature-qr:transformDebugUnitTestClassesWithAsm UP-TO-DATE
> Task :feature-qr:testDebugUnitTest NO-SOURCE
> Task :feature-qr:preReleaseUnitTestBuild UP-TO-DATE
> Task :feature-qr:generateReleaseUnitTestStubRFile UP-TO-DATE
> Task :feature-qr:kaptGenerateStubsReleaseUnitTestKotlin UP-TO-DATE
> Task :feature-qr:kaptReleaseUnitTestKotlin UP-TO-DATE
> Task :feature-qr:compileReleaseUnitTestKotlin NO-SOURCE
> Task :feature-qr:javaPreCompileReleaseUnitTest UP-TO-DATE
> Task :feature-qr:compileReleaseUnitTestJavaWithJavac NO-SOURCE
> Task :feature-qr:hiltAggregateDepsReleaseUnitTest UP-TO-DATE
> Task :feature-qr:hiltJavaCompileReleaseUnitTest NO-SOURCE
> Task :feature-qr:processReleaseUnitTestJavaRes NO-SOURCE
> Task :feature-qr:transformReleaseUnitTestClassesWithAsm UP-TO-DATE
> Task :feature-qr:testReleaseUnitTest NO-SOURCE
> Task :feature-qr:test UP-TO-DATE
> Task :feature-quiz:preDebugUnitTestBuild UP-TO-DATE
> Task :feature-quiz:generateDebugUnitTestStubRFile UP-TO-DATE
> Task :feature-quiz:kaptGenerateStubsDebugUnitTestKotlin UP-TO-DATE
> Task :feature-quiz:kaptDebugUnitTestKotlin UP-TO-DATE
> Task :feature-quiz:compileDebugUnitTestKotlin NO-SOURCE
> Task :feature-quiz:javaPreCompileDebugUnitTest UP-TO-DATE
> Task :feature-quiz:compileDebugUnitTestJavaWithJavac NO-SOURCE
> Task :feature-quiz:hiltAggregateDepsDebugUnitTest UP-TO-DATE
> Task :feature-quiz:hiltJavaCompileDebugUnitTest NO-SOURCE
> Task :feature-quiz:processDebugUnitTestJavaRes NO-SOURCE
> Task :feature-quiz:transformDebugUnitTestClassesWithAsm UP-TO-DATE
> Task :feature-quiz:testDebugUnitTest NO-SOURCE
> Task :feature-quiz:preReleaseUnitTestBuild UP-TO-DATE
> Task :feature-quiz:generateReleaseUnitTestStubRFile UP-TO-DATE
> Task :feature-quiz:kaptGenerateStubsReleaseUnitTestKotlin UP-TO-DATE
> Task :feature-quiz:kaptReleaseUnitTestKotlin UP-TO-DATE
> Task :feature-quiz:compileReleaseUnitTestKotlin NO-SOURCE
> Task :feature-quiz:javaPreCompileReleaseUnitTest UP-TO-DATE
> Task :feature-quiz:compileReleaseUnitTestJavaWithJavac NO-SOURCE
> Task :feature-quiz:hiltAggregateDepsReleaseUnitTest UP-TO-DATE
> Task :feature-quiz:hiltJavaCompileReleaseUnitTest NO-SOURCE
> Task :feature-quiz:processReleaseUnitTestJavaRes NO-SOURCE
> Task :feature-quiz:transformReleaseUnitTestClassesWithAsm UP-TO-DATE
> Task :feature-quiz:testReleaseUnitTest NO-SOURCE
> Task :feature-quiz:test UP-TO-DATE
> Task :feature-training:preDebugUnitTestBuild UP-TO-DATE
> Task :feature-training:generateDebugUnitTestStubRFile UP-TO-DATE
> Task :feature-training:kaptGenerateStubsDebugUnitTestKotlin UP-TO-DATE
> Task :feature-training:kaptDebugUnitTestKotlin UP-TO-DATE
> Task :feature-training:compileDebugUnitTestKotlin NO-SOURCE
> Task :feature-training:javaPreCompileDebugUnitTest UP-TO-DATE
> Task :feature-training:compileDebugUnitTestJavaWithJavac NO-SOURCE
> Task :feature-training:hiltAggregateDepsDebugUnitTest UP-TO-DATE
> Task :feature-training:hiltJavaCompileDebugUnitTest NO-SOURCE
> Task :feature-training:processDebugUnitTestJavaRes NO-SOURCE
> Task :feature-training:transformDebugUnitTestClassesWithAsm UP-TO-DATE
> Task :feature-training:testDebugUnitTest NO-SOURCE
> Task :feature-training:preReleaseUnitTestBuild UP-TO-DATE
> Task :feature-training:generateReleaseUnitTestStubRFile UP-TO-DATE
> Task :feature-training:kaptGenerateStubsReleaseUnitTestKotlin UP-TO-DATE
> Task :feature-training:kaptReleaseUnitTestKotlin UP-TO-DATE
> Task :feature-training:compileReleaseUnitTestKotlin NO-SOURCE
> Task :feature-training:javaPreCompileReleaseUnitTest UP-TO-DATE
> Task :feature-training:compileReleaseUnitTestJavaWithJavac NO-SOURCE
> Task :feature-training:hiltAggregateDepsReleaseUnitTest UP-TO-DATE
> Task :feature-training:hiltJavaCompileReleaseUnitTest NO-SOURCE
> Task :feature-training:processReleaseUnitTestJavaRes NO-SOURCE
> Task :feature-training:transformReleaseUnitTestClassesWithAsm UP-TO-DATE
> Task :feature-training:testReleaseUnitTest NO-SOURCE
> Task :feature-training:test UP-TO-DATE

BUILD SUCCESSFUL in 13s
529 actionable tasks: 529 up-to-date
  ✓ exit code 0

$ ./gradlew jacocoTestReport

FAILURE: Build failed with an exception.

* What went wrong:
Task 'jacocoTestReport' not found in root project 'SafePlant' and its subprojects.

* Try:
> Run gradlew tasks to get a list of available tasks.
> For more on name expansion, please refer to https://docs.gradle.org/8.10.2/userguide/command_line_interface.html#sec:name_abbreviation in the Gradle documentation.
> Run with --stacktrace option to get the stack trace.
> Run with --info or --debug option to get more log output.
> Run with --scan to get full insights.
> Get more help at https://help.gradle.org.

BUILD FAILED in 6s
  ✗ exit code 1

$ ./gradlew ktlintCheck detekt
> Task :app:loadKtlintReporters UP-TO-DATE
> Task :app:runKtlintCheckOverAndroidTestDebugSourceSet NO-SOURCE
> Task :app:ktlintAndroidTestDebugSourceSetCheck SKIPPED
> Task :app:runKtlintCheckOverAndroidTestReleaseSourceSet NO-SOURCE
> Task :app:ktlintAndroidTestReleaseSourceSetCheck SKIPPED
> Task :app:runKtlintCheckOverAndroidTestSourceSet NO-SOURCE
> Task :app:ktlintAndroidTestSourceSetCheck SKIPPED
> Task :app:runKtlintCheckOverDebugSourceSet NO-SOURCE
> Task :app:ktlintDebugSourceSetCheck SKIPPED
> Task :app:runKtlintCheckOverKotlinScripts UP-TO-DATE
> Task :app:ktlintKotlinScriptCheck UP-TO-DATE
> Task :app:runKtlintCheckOverMainSourceSet UP-TO-DATE
> Task :app:ktlintMainSourceSetCheck UP-TO-DATE
> Task :app:runKtlintCheckOverReleaseSourceSet NO-SOURCE
> Task :app:ktlintReleaseSourceSetCheck SKIPPED
> Task :app:runKtlintCheckOverTestDebugSourceSet NO-SOURCE
> Task :app:ktlintTestDebugSourceSetCheck SKIPPED
> Task :app:runKtlintCheckOverTestFixturesDebugSourceSet NO-SOURCE
> Task :app:ktlintTestFixturesDebugSourceSetCheck SKIPPED
> Task :app:runKtlintCheckOverTestFixturesReleaseSourceSet NO-SOURCE
> Task :app:ktlintTestFixturesReleaseSourceSetCheck SKIPPED
> Task :app:runKtlintCheckOverTestFixturesSourceSet NO-SOURCE
> Task :app:ktlintTestFixturesSourceSetCheck SKIPPED
> Task :app:runKtlintCheckOverTestReleaseSourceSet NO-SOURCE
> Task :app:ktlintTestReleaseSourceSetCheck SKIPPED
> Task :app:runKtlintCheckOverTestSourceSet NO-SOURCE
> Task :app:ktlintTestSourceSetCheck SKIPPED
> Task :app:ktlintCheck UP-TO-DATE
> Task :app:detekt UP-TO-DATE

BUILD SUCCESSFUL in 4s
6 actionable tasks: 6 up-to-date
  ✓ exit code 0

$ ls -la app/src/main/assets/
total 8
drwxr-xr-x 2 yano4 yano4 4096 Apr 23 14:09 .
drwxr-xr-x 5 yano4 yano4 4096 Apr 23 14:09 ..
  ✓ exit code 0

$ adb shell am start -n com.safeplant/.MainActivity
* daemon not running; starting now at tcp:5037
* daemon started successfully
adb: no devices/emulators found
  ✗ exit code 1

**LLM:** 
## Анализ проблемы

Я проанализировал результаты проверок и обнаружил несколько проблем:

1. **Проблема с тестами**: Отсутствуют тестовые файлы, что соответствует ошибке "Test files present (No test files found)".
2. **Проблема с Jacoco**: Задача `jacocoTestReport` не найдена, что означает отсутствие конфигурации для покрытия тестами.
3. **Проблема с ProGuard**: В логе сборки есть предупреждение "Supplied proguard configuration does not exist: /home/yano4/project3/app/proguard-rules.pro".
4. **...

**Proposed files:** `build.gradle.kts`, `app/build.gradle.kts`, `app/src/test/java/com/safeplant/VersionCheckerTest.kt`, `core-database/src/test/java/com/safeplant/core/database/DatabaseTest.kt`, `core-database/src/androidTest/java/com/safeplant/core/database/DatabaseMigrationTest.kt`, `feature-quiz/src/test/java/com/safeplant/feature/quiz/QuizViewModelTest.kt`, `feature-qr/src/test/java/com/safeplant/feature/qr/QRValidatorTest.kt`, `config/detekt/detekt.yml`

**Applied:** `app/src/test/java/com/safeplant/VersionCheckerTest.kt` ✓ (2026-04-24 01:25)

**Applied:** `core-database/src/test/java/com/safeplant/core/database/DatabaseTest.kt` ✓ (2026-04-24 01:26)

**Applied:** `feature-quiz/src/test/java/com/safeplant/feature/quiz/QuizViewModelTest.kt` ✓ (2026-04-24 01:26)

**Applied:** `feature-qr/src/test/java/com/safeplant/feature/qr/QRValidatorTest.kt` ✓ (2026-04-24 01:26)

**Applied:** `build.gradle.kts` ✓ (2026-04-24 01:26)

**Applied:** `app/build.gradle.kts` ✓ (2026-04-24 01:26)

**Applied:** `core-database/src/androidTest/java/com/safeplant/core/database/DatabaseMigrationTest.kt` ✓ (2026-04-24 01:26)

**Applied:** `config/detekt/detekt.yml` ✓ (2026-04-24 01:26)

---

## 2026-04-24 01:28

**User:** Оценка предложенных изменений и рекомендации
Вы предоставили анализ проблем и предложили добавить тесты и JaCoCo. Это правильное направление для соответствия конституции (70% покрытия бизнес-логики). Однако в текущем состоянии проекта нет реальной бизнес-логики (везде заглушки), поэтому писать тесты сейчас — преждевременно. Тем не менее, настроить JaCoCo и подготовить инфраструктуру можно заранее.

🔍 Ошибки в предложенных фрагментах
Корневой build.gradle.kts – дублирование плагинов. У вас написано:

kotlin
id("com.android.application")
id("org.jetbrains.kotlin.android")
...
id("org.jlleitschuh.gradle.ktlint")
Это недопустимо – плагины уже объявлены с apply false, повторное объявление без версии вызовет ошибку. Нужно удалить дубликаты.

id("jacoco") – плагин JaCoCo подключается так:

kotlin
plugins {
    id("jacoco")
}
Но лучше объявить его в корневом build.gradle.kts с версией, например:

kotlin
id("jacoco") version "0.8.12" apply false
а в app/build.gradle.kts применить id("jacoco").

Опечатка jaccoco везде – должно быть jacoco.

testInstrumentationRunnerArguments["jaccoco"] = "true" – редко используется. Обычно достаточно enableAndroidTestCoverage = true в debug buildType.

Задача jacocoTestReport – указаны неверные пути к .exec файлам. Стандартные:

"${buildDir}/jacoco/testDebugUnitTest.exec"

"${buildDir}/outputs/code_coverage/debugAndroidTest/connected/coverage.ec"
Без реальных тестов задача всё равно будет падать.

detekt у вас настроен, но в app/build.gradle.kts не подключён плагин. Добавьте id("io.gitlab.arturbosch.detekt") в блок plugins.

✅ Прагматичное решение для текущего этапа
Поскольку приложение уже собирается и работает (APK получен), рекомендую отложить тесты и JaCoCo до момента, когда вы начнёте писать реальную логику. Сейчас это только задержит разработку.

Тем не менее, если вы хотите заранее настроить инфраструктуру, вот исправленная версия app/build.gradle.kts (только отличия от вашего текущего рабочего файла):

kotlin
plugins {
    // ... существующие плагины
    id("jacoco")
    id("io.gitlab.arturbosch.detekt")  // добавьте, если ещё нет
}

android {
    buildTypes {
        debug {
            enableAndroidTestCoverage = true
        }
    }
}

jacoco {
    toolVersion = "0.8.12"
}

tasks.register<JacocoReport>("jacocoTestReport") {
    dependsOn("testDebugUnitTest", "connectedDebugAndroidTest")
    reports {
        xml.required.set(true)
        html.required.set(true)
    }
    // Пути к исходникам и классам – заполните позже, когда появятся реальные классы
    // sourceDirectories.setFrom(...)
    // classDirectories.setFrom(...)
    // executionData.setFrom(...)
}

ktlint {
    version.set("1.5.0")
    android.set(true)
    ignoreFailures.set(false)
    reporters {
        reporter(org.jlleitschuh.gradle.ktlint.reporter.ReporterType.PLAIN)
        reporter(org.jlleitschuh.gradle.ktlint.reporter.ReporterType.CHECKSTYLE)
    }
}

detekt {
    config = files("${rootDir}/config/detekt/detekt.yml")
    buildUponDefaultConfig = true
}
🧹 Очистка корневого build.gradle.kts
Удалите дублирующиеся строки. Он должен выглядеть так:

kotlin
plugins {
    id("com.android.application") version "8.8.2" apply false
    id("org.jetbrains.kotlin.android") version "2.1.20" apply false
    id("com.google.dagger.hilt.android") version "2.51.1" apply false
    id("com.google.devtools.ksp") version "2.1.20-2.0.0" apply false
    id("org.jetbrains.kotlin.plugin.compose") version "2.1.20" apply false
    id("org.jlleitschuh.gradle.ktlint") version "12.1.2" apply false
    id("io.gitlab.arturbosch.detekt") version "1.23.8" apply false
    id("jacoco") version "0.8.12" apply false
}
📌 Заключение
Текущий APK работоспособен – вы можете устанавливать и тестировать.

Тесты и JaCoCo – следующая итерация. Сначала напишите реальную бизнес-логику (квиз, карту, QR), затем добавьте модульные тесты для неё.

Если решите внедрить JaCoCo немедленно, используйте исправленный код выше, но помните – отчёт будет пустым.

**LLM:** 
## Анализ и рекомендации

Вы абсолютно правы в своих замечаниях. Я допустил несколько ошибок в предложенных решениях:

1. **Дублирование плагинов** - действительно, в корневом build.gradle.kts плагины уже объявлены с apply false, и повторное объявление вызовет ошибку.
2. **Опечатки в "jacoco"** - везде написал "jaccoco", что неверно.
3. **Неверные пути к .exec файлам** - указаны некорректные пути для Jacoco.
4. **Отсутствие реальной бизнес-логики** - писать тесты для заглушек действительно преж...

**Proposed files:** (no files)

---

## 2026-04-24 21:29

**User:** ── Structural Checks ──
  ✗ Test files present  (No test files found)
  ✓ Specification filled  (2393 chars)
  ✓ All stages implemented  (5/5 stages)

── Verify Commands ──

$ ./gradlew build
> Task :app:preBuild UP-TO-DATE
> Task :app:preDebugBuild UP-TO-DATE
> Task :app:mergeDebugNativeDebugMetadata NO-SOURCE
> Task :app:checkKotlinGradlePluginConfigurationErrors SKIPPED
> Task :core-database:preBuild UP-TO-DATE
> Task :core-database:preDebugBuild UP-TO-DATE
> Task :core-database:writeDebugAarMetadata UP-TO-DATE
> Task :core-mapping:preBuild UP-TO-DATE
> Task :core-mapping:preDebugBuild UP-TO-DATE
> Task :core-mapping:writeDebugAarMetadata UP-TO-DATE
> Task :core-navigation:preBuild UP-TO-DATE
> Task :core-navigation:preDebugBuild UP-TO-DATE
> Task :core-navigation:writeDebugAarMetadata UP-TO-DATE
> Task :core-security:preBuild UP-TO-DATE
> Task :core-security:preDebugBuild UP-TO-DATE
> Task :core-security:writeDebugAarMetadata UP-TO-DATE
> Task :core-storage:preBuild UP-TO-DATE
> Task :core-storage:preDebugBuild UP-TO-DATE
> Task :core-storage:writeDebugAarMetadata UP-TO-DATE
> Task :feature-map:preBuild UP-TO-DATE
> Task :feature-map:preDebugBuild UP-TO-DATE
> Task :feature-map:writeDebugAarMetadata UP-TO-DATE
> Task :feature-profile:preBuild UP-TO-DATE
> Task :feature-profile:preDebugBuild UP-TO-DATE
> Task :feature-profile:writeDebugAarMetadata UP-TO-DATE
> Task :feature-qr:preBuild UP-TO-DATE
> Task :feature-qr:preDebugBuild UP-TO-DATE
> Task :feature-qr:writeDebugAarMetadata UP-TO-DATE
> Task :feature-quiz:preBuild UP-TO-DATE
> Task :feature-quiz:preDebugBuild UP-TO-DATE
> Task :feature-quiz:writeDebugAarMetadata UP-TO-DATE
> Task :feature-training:preBuild UP-TO-DATE
> Task :feature-training:preDebugBuild UP-TO-DATE
> Task :feature-training:writeDebugAarMetadata UP-TO-DATE
> Task :app:checkDebugAarMetadata UP-TO-DATE
> Task :app:generateDebugResValues UP-TO-DATE
> Task :core-database:generateDebugResValues UP-TO-DATE
> Task :core-database:generateDebugResources UP-TO-DATE
> Task :core-database:packageDebugResources UP-TO-DATE
> Task :core-mapping:generateDebugResValues UP-TO-DATE
> Task :core-mapping:generateDebugResources UP-TO-DATE
> Task :core-mapping:packageDebugResources UP-TO-DATE
> Task :core-navigation:generateDebugResValues UP-TO-DATE
> Task :core-navigation:generateDebugResources UP-TO-DATE
> Task :core-navigation:packageDebugResources UP-TO-DATE
> Task :core-security:generateDebugResValues UP-TO-DATE
> Task :core-security:generateDebugResources UP-TO-DATE
> Task :core-security:packageDebugResources UP-TO-DATE
> Task :core-storage:generateDebugResValues UP-TO-DATE
> Task :core-storage:generateDebugResources UP-TO-DATE
> Task :core-storage:packageDebugResources UP-TO-DATE
> Task :feature-map:generateDebugResValues UP-TO-DATE
> Task :feature-map:generateDebugResources UP-TO-DATE
> Task :feature-map:packageDebugResources UP-TO-DATE
> Task :feature-profile:generateDebugResValues UP-TO-DATE
> Task :feature-profile:generateDebugResources UP-TO-DATE
> Task :feature-profile:packageDebugResources UP-TO-DATE
> Task :feature-qr:generateDebugResValues UP-TO-DATE
> Task :feature-qr:generateDebugResources UP-TO-DATE
> Task :feature-qr:packageDebugResources UP-TO-DATE
> Task :feature-quiz:generateDebugResValues UP-TO-DATE
> Task :feature-quiz:generateDebugResources UP-TO-DATE
> Task :feature-quiz:packageDebugResources UP-TO-DATE
> Task :feature-training:generateDebugResValues UP-TO-DATE
> Task :feature-training:generateDebugResources UP-TO-DATE
> Task :feature-training:packageDebugResources UP-TO-DATE
> Task :app:mapDebugSourceSetPaths UP-TO-DATE
> Task :app:generateDebugResources UP-TO-DATE
> Task :app:mergeDebugResources UP-TO-DATE
> Task :app:packageDebugResources UP-TO-DATE
> Task :app:parseDebugLocalResources UP-TO-DATE
> Task :app:createDebugCompatibleScreenManifests UP-TO-DATE
> Task :app:extractDeepLinksDebug UP-TO-DATE
> Task :core-database:extractDeepLinksDebug UP-TO-DATE
> Task :core-database:processDebugManifest UP-TO-DATE
> Task :core-mapping:extractDeepLinksDebug UP-TO-DATE
> Task :core-mapping:processDebugManifest UP-TO-DATE
> Task :core-navigation:extractDeepLinksDebug UP-TO-DATE
> Task :core-navigation:processDebugManifest UP-TO-DATE
> Task :core-security:extractDeepLinksDebug UP-TO-DATE
> Task :core-security:processDebugManifest UP-TO-DATE
> Task :core-storage:extractDeepLinksDebug UP-TO-DATE
> Task :core-storage:processDebugManifest UP-TO-DATE
> Task :feature-map:extractDeepLinksDebug UP-TO-DATE
> Task :feature-map:processDebugManifest UP-TO-DATE
> Task :feature-profile:extractDeepLinksDebug UP-TO-DATE
> Task :feature-profile:processDebugManifest UP-TO-DATE
> Task :feature-qr:extractDeepLinksDebug UP-TO-DATE
> Task :feature-qr:processDebugManifest UP-TO-DATE
> Task :feature-quiz:extractDeepLinksDebug UP-TO-DATE
> Task :feature-quiz:processDebugManifest UP-TO-DATE
> Task :feature-training:extractDeepLinksDebug UP-TO-DATE
> Task :feature-training:processDebugManifest UP-TO-DATE
> Task :app:processDebugMainManifest UP-TO-DATE
> Task :app:processDebugManifest UP-TO-DATE
> Task :app:processDebugManifestForPackage UP-TO-DATE
> Task :core-database:compileDebugLibraryResources UP-TO-DATE
> Task :core-database:parseDebugLocalResources UP-TO-DATE
> Task :core-database:generateDebugRFile UP-TO-DATE
> Task :core-mapping:compileDebugLibraryResources UP-TO-DATE
> Task :core-mapping:parseDebugLocalResources UP-TO-DATE
> Task :core-mapping:generateDebugRFile UP-TO-DATE
> Task :core-navigation:compileDebugLibraryResources UP-TO-DATE
> Task :core-navigation:parseDebugLocalResources UP-TO-DATE
> Task :core-navigation:generateDebugRFile UP-TO-DATE
> Task :core-security:compileDebugLibraryResources UP-TO-DATE
> Task :core-security:parseDebugLocalResources UP-TO-DATE
> Task :core-security:generateDebugRFile UP-TO-DATE
> Task :core-storage:compileDebugLibraryResources UP-TO-DATE
> Task :core-storage:parseDebugLocalResources UP-TO-DATE
> Task :core-storage:generateDebugRFile UP-TO-DATE
> Task :feature-map:compileDebugLibraryResources UP-TO-DATE
> Task :feature-map:parseDebugLocalResources UP-TO-DATE
> Task :feature-map:generateDebugRFile UP-TO-DATE
> Task :feature-profile:compileDebugLibraryResources UP-TO-DATE
> Task :feature-profile:parseDebugLocalResources UP-TO-DATE
> Task :feature-profile:generateDebugRFile UP-TO-DATE
> Task :feature-qr:compileDebugLibraryResources UP-TO-DATE
> Task :feature-qr:parseDebugLocalResources UP-TO-DATE
> Task :feature-qr:generateDebugRFile UP-TO-DATE
> Task :feature-quiz:compileDebugLibraryResources UP-TO-DATE
> Task :feature-quiz:parseDebugLocalResources UP-TO-DATE
> Task :feature-quiz:generateDebugRFile UP-TO-DATE
> Task :feature-training:compileDebugLibraryResources UP-TO-DATE
> Task :feature-training:parseDebugLocalResources UP-TO-DATE
> Task :feature-training:generateDebugRFile UP-TO-DATE
> Task :app:processDebugResources UP-TO-DATE
> Task :core-database:checkKotlinGradlePluginConfigurationErrors SKIPPED
> Task :core-database:kspDebugKotlin UP-TO-DATE
> Task :core-database:kaptGenerateStubsDebugKotlin UP-TO-DATE
> Task :core-database:kaptDebugKotlin SKIPPED
> Task :core-database:compileDebugKotlin UP-TO-DATE
> Task :core-database:javaPreCompileDebug UP-TO-DATE
> Task :core-database:compileDebugJavaWithJavac NO-SOURCE
> Task :core-database:bundleLibCompileToJarDebug UP-TO-DATE
> Task :core-mapping:checkKotlinGradlePluginConfigurationErrors SKIPPED
> Task :core-mapping:kaptGenerateStubsDebugKotlin UP-TO-DATE
> Task :core-mapping:kaptDebugKotlin SKIPPED
> Task :core-mapping:compileDebugKotlin NO-SOURCE
> Task :core-mapping:javaPreCompileDebug UP-TO-DATE
> Task :core-mapping:compileDebugJavaWithJavac NO-SOURCE
> Task :core-mapping:bundleLibCompileToJarDebug UP-TO-DATE
> Task :core-navigation:checkKotlinGradlePluginConfigurationErrors SKIPPED
> Task :core-navigation:kaptGenerateStubsDebugKotlin UP-TO-DATE
> Task :core-navigation:kaptDebugKotlin SKIPPED
> Task :core-navigation:compileDebugKotlin UP-TO-DATE
> Task :core-navigation:javaPreCompileDebug UP-TO-DATE
> Task :core-navigation:compileDebugJavaWithJavac NO-SOURCE
> Task :core-navigation:bundleLibCompileToJarDebug UP-TO-DATE
> Task :core-security:checkKotlinGradlePluginConfigurationErrors SKIPPED
> Task :core-security:kaptGenerateStubsDebugKotlin UP-TO-DATE
> Task :core-security:kaptDebugKotlin SKIPPED
> Task :core-security:compileDebugKotlin UP-TO-DATE
> Task :core-security:javaPreCompileDebug UP-TO-DATE
> Task :core-security:compileDebugJavaWithJavac NO-SOURCE
> Task :core-security:bundleLibCompileToJarDebug UP-TO-DATE
> Task :core-storage:checkKotlinGradlePluginConfigurationErrors SKIPPED
> Task :core-storage:kaptGenerateStubsDebugKotlin UP-TO-DATE
> Task :core-storage:kaptDebugKotlin SKIPPED
> Task :core-storage:compileDebugKotlin UP-TO-DATE
> Task :core-storage:javaPreCompileDebug UP-TO-DATE
> Task :core-storage:compileDebugJavaWithJavac NO-SOURCE
> Task :core-storage:bundleLibCompileToJarDebug UP-TO-DATE
> Task :feature-map:checkKotlinGradlePluginConfigurationErrors SKIPPED
> Task :feature-map:kaptGenerateStubsDebugKotlin UP-TO-DATE
> Task :feature-map:kaptDebugKotlin SKIPPED
> Task :feature-map:compileDebugKotlin UP-TO-DATE
> Task :feature-map:javaPreCompileDebug UP-TO-DATE
> Task :feature-map:compileDebugJavaWithJavac NO-SOURCE
> Task :feature-map:bundleLibCompileToJarDebug UP-TO-DATE
> Task :feature-profile:checkKotlinGradlePluginConfigurationErrors SKIPPED
> Task :feature-profile:kaptGenerateStubsDebugKotlin UP-TO-DATE
> Task :feature-profile:kaptDebugKotlin SKIPPED
> Task :feature-profile:compileDebugKotlin UP-TO-DATE
> Task :feature-profile:javaPreCompileDebug UP-TO-DATE
> Task :feature-profile:compileDebugJavaWithJavac NO-SOURCE
> Task :feature-profile:bundleLibCompileToJarDebug UP-TO-DATE
> Task :feature-qr:checkKotlinGradlePluginConfigurationErrors SKIPPED
> Task :feature-qr:kaptGenerateStubsDebugKotlin UP-TO-DATE
> Task :feature-qr:kaptDebugKotlin SKIPPED
> Task :feature-qr:compileDebugKotlin UP-TO-DATE
> Task :feature-qr:javaPreCompileDebug UP-TO-DATE
> Task :feature-qr:compileDebugJavaWithJavac NO-SOURCE
> Task :feature-qr:bundleLibCompileToJarDebug UP-TO-DATE
> Task :feature-quiz:checkKotlinGradlePluginConfigurationErrors SKIPPED
> Task :feature-quiz:kaptGenerateStubsDebugKotlin UP-TO-DATE
> Task :feature-quiz:kaptDebugKotlin SKIPPED
> Task :feature-quiz:compileDebugKotlin UP-TO-DATE
> Task :feature-quiz:javaPreCompileDebug UP-TO-DATE
> Task :feature-quiz:compileDebugJavaWithJavac NO-SOURCE
> Task :feature-quiz:bundleLibCompileToJarDebug UP-TO-DATE
> Task :feature-training:checkKotlinGradlePluginConfigurationErrors SKIPPED
> Task :feature-training:kaptGenerateStubsDebugKotlin UP-TO-DATE
> Task :feature-training:kaptDebugKotlin SKIPPED
> Task :feature-training:compileDebugKotlin UP-TO-DATE
> Task :feature-training:javaPreCompileDebug UP-TO-DATE
> Task :feature-training:compileDebugJavaWithJavac NO-SOURCE
> Task :feature-training:bundleLibCompileToJarDebug UP-TO-DATE
> Task :app:kaptGenerateStubsDebugKotlin UP-TO-DATE
> Task :app:kaptDebugKotlin SKIPPED
> Task :app:compileDebugKotlin UP-TO-DATE
> Task :app:javaPreCompileDebug UP-TO-DATE
> Task :app:compileDebugJavaWithJavac NO-SOURCE
> Task :app:mergeDebugShaders UP-TO-DATE
> Task :app:compileDebugShaders NO-SOURCE
> Task :app:generateDebugAssets UP-TO-DATE
> Task :core-database:mergeDebugShaders UP-TO-DATE
> Task :core-database:compileDebugShaders NO-SOURCE
> Task :core-database:generateDebugAssets UP-TO-DATE
> Task :core-database:packageDebugAssets UP-TO-DATE
> Task :core-mapping:mergeDebugShaders UP-TO-DATE
> Task :core-mapping:compileDebugShaders NO-SOURCE
> Task :core-mapping:generateDebugAssets UP-TO-DATE
> Task :core-mapping:packageDebugAssets UP-TO-DATE
> Task :core-navigation:mergeDebugShaders UP-TO-DATE
> Task :core-navigation:compileDebugShaders NO-SOURCE
> Task :core-navigation:generateDebugAssets UP-TO-DATE
> Task :core-navigation:packageDebugAssets UP-TO-DATE
> Task :core-security:mergeDebugShaders UP-TO-DATE
> Task :core-security:compileDebugShaders NO-SOURCE
> Task :core-security:generateDebugAssets UP-TO-DATE
> Task :core-security:packageDebugAssets UP-TO-DATE
> Task :core-storage:mergeDebugShaders UP-TO-DATE
> Task :core-storage:compileDebugShaders NO-SOURCE
> Task :core-storage:generateDebugAssets UP-TO-DATE
> Task :core-storage:packageDebugAssets UP-TO-DATE
> Task :feature-map:mergeDebugShaders UP-TO-DATE
> Task :feature-map:compileDebugShaders NO-SOURCE
> Task :feature-map:generateDebugAssets UP-TO-DATE
> Task :feature-map:packageDebugAssets UP-TO-DATE
> Task :feature-profile:mergeDebugShaders UP-TO-DATE
> Task :feature-profile:compileDebugShaders NO-SOURCE
> Task :feature-profile:generateDebugAssets UP-TO-DATE
> Task :feature-profile:packageDebugAssets UP-TO-DATE
> Task :feature-qr:mergeDebugShaders UP-TO-DATE
> Task :feature-qr:compileDebugShaders NO-SOURCE
> Task :feature-qr:generateDebugAssets UP-TO-DATE
> Task :feature-qr:packageDebugAssets UP-TO-DATE
> Task :feature-quiz:mergeDebugShaders UP-TO-DATE
> Task :feature-quiz:compileDebugShaders NO-SOURCE
> Task :feature-quiz:generateDebugAssets UP-TO-DATE
> Task :feature-quiz:packageDebugAssets UP-TO-DATE
> Task :feature-training:mergeDebugShaders UP-TO-DATE
> Task :feature-training:compileDebugShaders NO-SOURCE
> Task :feature-training:generateDebugAssets UP-TO-DATE
> Task :feature-training:packageDebugAssets UP-TO-DATE
> Task :app:mergeDebugAssets UP-TO-DATE
> Task :app:compressDebugAssets UP-TO-DATE
> Task :core-navigation:bundleLibRuntimeToJarDebug UP-TO-DATE
> Task :feature-quiz:bundleLibRuntimeToJarDebug UP-TO-DATE
> Task :feature-map:bundleLibRuntimeToJarDebug UP-TO-DATE
> Task :feature-training:bundleLibRuntimeToJarDebug UP-TO-DATE
> Task :feature-qr:bundleLibRuntimeToJarDebug UP-TO-DATE
> Task :feature-profile:bundleLibRuntimeToJarDebug UP-TO-DATE
> Task :core-database:bundleLibRuntimeToJarDebug UP-TO-DATE
> Task :core-storage:bundleLibRuntimeToJarDebug UP-TO-DATE
> Task :core-security:bundleLibRuntimeToJarDebug UP-TO-DATE
> Task :core-mapping:bundleLibRuntimeToJarDebug UP-TO-DATE
> Task :app:desugarDebugFileDependencies UP-TO-DATE
> Task :app:jacocoDebug UP-TO-DATE
> Task :app:dexBuilderDebug UP-TO-DATE
> Task :app:mergeDebugGlobalSynthetics UP-TO-DATE
> Task :app:generateDebugJacocoPropertiesFile UP-TO-DATE
> Task :app:processDebugJavaRes UP-TO-DATE
> Task :core-database:processDebugJavaRes UP-TO-DATE
> Task :core-mapping:processDebugJavaRes NO-SOURCE
> Task :core-navigation:processDebugJavaRes UP-TO-DATE
> Task :core-security:processDebugJavaRes UP-TO-DATE
> Task :core-storage:processDebugJavaRes UP-TO-DATE
> Task :feature-map:processDebugJavaRes UP-TO-DATE
> Task :feature-profile:processDebugJavaRes UP-TO-DATE
> Task :feature-qr:processDebugJavaRes UP-TO-DATE
> Task :feature-quiz:processDebugJavaRes UP-TO-DATE
> Task :feature-training:processDebugJavaRes UP-TO-DATE
> Task :app:mergeDebugJavaResource UP-TO-DATE
> Task :app:checkDebugDuplicateClasses UP-TO-DATE
> Task :app:mergeExtDexDebug UP-TO-DATE
> Task :core-navigation:bundleLibRuntimeToDirDebug UP-TO-DATE
> Task :feature-quiz:bundleLibRuntimeToDirDebug UP-TO-DATE
> Task :feature-map:bundleLibRuntimeToDirDebug UP-TO-DATE
> Task :feature-training:bundleLibRuntimeToDirDebug UP-TO-DATE
> Task :feature-qr:bundleLibRuntimeToDirDebug UP-TO-DATE
> Task :feature-profile:bundleLibRuntimeToDirDebug UP-TO-DATE
> Task :core-database:bundleLibRuntimeToDirDebug UP-TO-DATE
> Task :core-storage:bundleLibRuntimeToDirDebug UP-TO-DATE
> Task :core-security:bundleLibRuntimeToDirDebug UP-TO-DATE
> Task :core-mapping:bundleLibRuntimeToDirDebug UP-TO-DATE
> Task :app:mergeProjectDexDebug UP-TO-DATE
> Task :app:mergeDebugJniLibFolders UP-TO-DATE
> Task :app:mergeLibDexDebug UP-TO-DATE
> Task :core-database:mergeDebugJniLibFolders UP-TO-DATE
> Task :core-database:mergeDebugNativeLibs NO-SOURCE
> Task :core-database:copyDebugJniLibsProjectOnly UP-TO-DATE
> Task :core-mapping:mergeDebugJniLibFolders UP-TO-DATE
> Task :core-mapping:mergeDebugNativeLibs NO-SOURCE
> Task :core-mapping:copyDebugJniLibsProjectOnly UP-TO-DATE
> Task :core-navigation:mergeDebugJniLibFolders UP-TO-DATE
> Task :core-navigation:mergeDebugNativeLibs NO-SOURCE
> Task :core-navigation:copyDebugJniLibsProjectOnly UP-TO-DATE
> Task :core-security:mergeDebugJniLibFolders UP-TO-DATE
> Task :core-security:mergeDebugNativeLibs NO-SOURCE
> Task :core-security:copyDebugJniLibsProjectOnly UP-TO-DATE
> Task :core-storage:mergeDebugJniLibFolders UP-TO-DATE
> Task :core-storage:mergeDebugNativeLibs NO-SOURCE
> Task :core-storage:copyDebugJniLibsProjectOnly UP-TO-DATE
> Task :feature-map:mergeDebugJniLibFolders UP-TO-DATE
> Task :feature-map:mergeDebugNativeLibs NO-SOURCE
> Task :feature-map:copyDebugJniLibsProjectOnly UP-TO-DATE
> Task :feature-profile:mergeDebugJniLibFolders UP-TO-DATE
> Task :feature-profile:mergeDebugNativeLibs NO-SOURCE
> Task :feature-profile:copyDebugJniLibsProjectOnly UP-TO-DATE
> Task :feature-qr:mergeDebugJniLibFolders UP-TO-DATE
> Task :feature-qr:mergeDebugNativeLibs NO-SOURCE
> Task :feature-qr:copyDebugJniLibsProjectOnly UP-TO-DATE
> Task :feature-quiz:mergeDebugJniLibFolders UP-TO-DATE
> Task :feature-quiz:mergeDebugNativeLibs NO-SOURCE
> Task :feature-quiz:copyDebugJniLibsProjectOnly UP-TO-DATE
> Task :feature-training:mergeDebugJniLibFolders UP-TO-DATE
> Task :feature-training:mergeDebugNativeLibs NO-SOURCE
> Task :feature-training:copyDebugJniLibsProjectOnly UP-TO-DATE
> Task :app:mergeDebugNativeLibs UP-TO-DATE
> Task :app:stripDebugDebugSymbols UP-TO-DATE
> Task :app:validateSigningDebug UP-TO-DATE
> Task :app:writeDebugAppMetadata UP-TO-DATE
> Task :app:writeDebugSigningConfigVersions UP-TO-DATE
> Task :app:packageDebug UP-TO-DATE
> Task :app:createDebugApkListingFileRedirect UP-TO-DATE
> Task :app:assembleDebug UP-TO-DATE
> Task :app:buildKotlinToolingMetadata UP-TO-DATE
> Task :app:preReleaseBuild UP-TO-DATE
> Task :core-database:preReleaseBuild UP-TO-DATE
> Task :core-database:writeReleaseAarMetadata UP-TO-DATE
> Task :core-mapping:preReleaseBuild UP-TO-DATE
> Task :core-mapping:writeReleaseAarMetadata UP-TO-DATE
> Task :core-navigation:preReleaseBuild UP-TO-DATE
> Task :core-navigation:writeReleaseAarMetadata UP-TO-DATE
> Task :core-security:preReleaseBuild UP-TO-DATE
> Task :core-security:writeReleaseAarMetadata UP-TO-DATE
> Task :core-storage:preReleaseBuild UP-TO-DATE
> Task :core-storage:writeReleaseAarMetadata UP-TO-DATE
> Task :feature-map:preReleaseBuild UP-TO-DATE
> Task :feature-map:writeReleaseAarMetadata UP-TO-DATE
> Task :feature-profile:preReleaseBuild UP-TO-DATE
> Task :feature-profile:writeReleaseAarMetadata UP-TO-DATE
> Task :feature-qr:preReleaseBuild UP-TO-DATE
> Task :feature-qr:writeReleaseAarMetadata UP-TO-DATE
> Task :feature-quiz:preReleaseBuild UP-TO-DATE
> Task :feature-quiz:writeReleaseAarMetadata UP-TO-DATE
> Task :feature-training:preReleaseBuild UP-TO-DATE
> Task :feature-training:writeReleaseAarMetadata UP-TO-DATE
> Task :app:checkReleaseAarMetadata UP-TO-DATE
> Task :app:generateReleaseResValues UP-TO-DATE
> Task :core-database:generateReleaseResValues UP-TO-DATE
> Task :core-database:generateReleaseResources UP-TO-DATE
> Task :core-database:packageReleaseResources UP-TO-DATE
> Task :core-mapping:generateReleaseResValues UP-TO-DATE
> Task :core-mapping:generateReleaseResources UP-TO-DATE
> Task :core-mapping:packageReleaseResources UP-TO-DATE
> Task :core-navigation:generateReleaseResValues UP-TO-DATE
> Task :core-navigation:generateReleaseResources UP-TO-DATE
> Task :core-navigation:packageReleaseResources UP-TO-DATE
> Task :core-security:generateReleaseResValues UP-TO-DATE
> Task :core-security:generateReleaseResources UP-TO-DATE
> Task :core-security:packageReleaseResources UP-TO-DATE
> Task :core-storage:generateReleaseResValues UP-TO-DATE
> Task :core-storage:generateReleaseResources UP-TO-DATE
> Task :core-storage:packageReleaseResources UP-TO-DATE
> Task :feature-map:generateReleaseResValues UP-TO-DATE
> Task :feature-map:generateReleaseResources UP-TO-DATE
> Task :feature-map:packageReleaseResources UP-TO-DATE
> Task :feature-profile:generateReleaseResValues UP-TO-DATE
> Task :feature-profile:generateReleaseResources UP-TO-DATE
> Task :feature-profile:packageReleaseResources UP-TO-DATE
> Task :feature-qr:generateReleaseResValues UP-TO-DATE
> Task :feature-qr:generateReleaseResources UP-TO-DATE
> Task :feature-qr:packageReleaseResources UP-TO-DATE
> Task :feature-quiz:generateReleaseResValues UP-TO-DATE
> Task :feature-quiz:generateReleaseResources UP-TO-DATE
> Task :feature-quiz:packageReleaseResources UP-TO-DATE
> Task :feature-training:generateReleaseResValues UP-TO-DATE
> Task :feature-training:generateReleaseResources UP-TO-DATE
> Task :feature-training:packageReleaseResources UP-TO-DATE
> Task :app:mapReleaseSourceSetPaths UP-TO-DATE
> Task :app:generateReleaseResources UP-TO-DATE
> Task :app:mergeReleaseResources UP-TO-DATE
> Task :app:packageReleaseResources UP-TO-DATE
> Task :app:parseReleaseLocalResources UP-TO-DATE
> Task :app:createReleaseCompatibleScreenManifests UP-TO-DATE
> Task :app:extractDeepLinksRelease UP-TO-DATE
> Task :core-database:extractDeepLinksRelease UP-TO-DATE
> Task :core-database:processReleaseManifest UP-TO-DATE
> Task :core-mapping:extractDeepLinksRelease UP-TO-DATE
> Task :core-mapping:processReleaseManifest UP-TO-DATE
> Task :core-navigation:extractDeepLinksRelease UP-TO-DATE
> Task :core-navigation:processReleaseManifest UP-TO-DATE
> Task :core-security:extractDeepLinksRelease UP-TO-DATE
> Task :core-security:processReleaseManifest UP-TO-DATE
> Task :core-storage:extractDeepLinksRelease UP-TO-DATE
> Task :core-storage:processReleaseManifest UP-TO-DATE
> Task :feature-map:extractDeepLinksRelease UP-TO-DATE
> Task :feature-map:processReleaseManifest UP-TO-DATE
> Task :feature-profile:extractDeepLinksRelease UP-TO-DATE
> Task :feature-profile:processReleaseManifest UP-TO-DATE
> Task :feature-qr:extractDeepLinksRelease UP-TO-DATE
> Task :feature-qr:processReleaseManifest UP-TO-DATE
> Task :feature-quiz:extractDeepLinksRelease UP-TO-DATE
> Task :feature-quiz:processReleaseManifest UP-TO-DATE
> Task :feature-training:extractDeepLinksRelease UP-TO-DATE
> Task :feature-training:processReleaseManifest UP-TO-DATE
> Task :app:processReleaseMainManifest UP-TO-DATE
> Task :app:processReleaseManifest UP-TO-DATE
> Task :app:processReleaseManifestForPackage UP-TO-DATE
> Task :core-database:compileReleaseLibraryResources UP-TO-DATE
> Task :core-database:parseReleaseLocalResources UP-TO-DATE
> Task :core-database:generateReleaseRFile UP-TO-DATE
> Task :core-mapping:compileReleaseLibraryResources UP-TO-DATE
> Task :core-mapping:parseReleaseLocalResources UP-TO-DATE
> Task :core-mapping:generateReleaseRFile UP-TO-DATE
> Task :core-navigation:compileReleaseLibraryResources UP-TO-DATE
> Task :core-navigation:parseReleaseLocalResources UP-TO-DATE
> Task :core-navigation:generateReleaseRFile UP-TO-DATE
> Task :core-security:compileReleaseLibraryResources UP-TO-DATE
> Task :core-security:parseReleaseLocalResources UP-TO-DATE
> Task :core-security:generateReleaseRFile UP-TO-DATE
> Task :core-storage:compileReleaseLibraryResources UP-TO-DATE
> Task :core-storage:parseReleaseLocalResources UP-TO-DATE
> Task :core-storage:generateReleaseRFile UP-TO-DATE
> Task :feature-map:compileReleaseLibraryResources UP-TO-DATE
> Task :feature-map:parseReleaseLocalResources UP-TO-DATE
> Task :feature-map:generateReleaseRFile UP-TO-DATE
> Task :feature-profile:compileReleaseLibraryResources UP-TO-DATE
> Task :feature-profile:parseReleaseLocalResources UP-TO-DATE
> Task :feature-profile:generateReleaseRFile UP-TO-DATE
> Task :feature-qr:compileReleaseLibraryResources UP-TO-DATE
> Task :feature-qr:parseReleaseLocalResources UP-TO-DATE
> Task :feature-qr:generateReleaseRFile UP-TO-DATE
> Task :feature-quiz:compileReleaseLibraryResources UP-TO-DATE
> Task :feature-quiz:parseReleaseLocalResources UP-TO-DATE
> Task :feature-quiz:generateReleaseRFile UP-TO-DATE
> Task :feature-training:compileReleaseLibraryResources UP-TO-DATE
> Task :feature-training:parseReleaseLocalResources UP-TO-DATE
> Task :feature-training:generateReleaseRFile UP-TO-DATE
> Task :app:processReleaseResources UP-TO-DATE
> Task :core-database:kspReleaseKotlin UP-TO-DATE
> Task :core-database:kaptGenerateStubsReleaseKotlin UP-TO-DATE
> Task :core-database:kaptReleaseKotlin SKIPPED
> Task :core-database:compileReleaseKotlin UP-TO-DATE
> Task :core-database:javaPreCompileRelease UP-TO-DATE
> Task :core-database:compileReleaseJavaWithJavac NO-SOURCE
> Task :core-database:bundleLibCompileToJarRelease UP-TO-DATE
> Task :core-mapping:kaptGenerateStubsReleaseKotlin UP-TO-DATE
> Task :core-mapping:kaptReleaseKotlin SKIPPED
> Task :core-mapping:compileReleaseKotlin NO-SOURCE
> Task :core-mapping:javaPreCompileRelease UP-TO-DATE
> Task :core-mapping:compileReleaseJavaWithJavac NO-SOURCE
> Task :core-mapping:bundleLibCompileToJarRelease UP-TO-DATE
> Task :core-navigation:kaptGenerateStubsReleaseKotlin UP-TO-DATE
> Task :core-navigation:kaptReleaseKotlin SKIPPED
> Task :core-navigation:compileReleaseKotlin UP-TO-DATE
> Task :core-navigation:javaPreCompileRelease UP-TO-DATE
> Task :core-navigation:compileReleaseJavaWithJavac NO-SOURCE
> Task :core-navigation:bundleLibCompileToJarRelease UP-TO-DATE
> Task :core-security:kaptGenerateStubsReleaseKotlin UP-TO-DATE
> Task :core-security:kaptReleaseKotlin SKIPPED
> Task :core-security:compileReleaseKotlin UP-TO-DATE
> Task :core-security:javaPreCompileRelease UP-TO-DATE
> Task :core-security:compileReleaseJavaWithJavac NO-SOURCE
> Task :core-security:bundleLibCompileToJarRelease UP-TO-DATE
> Task :core-storage:kaptGenerateStubsReleaseKotlin UP-TO-DATE
> Task :core-storage:kaptReleaseKotlin SKIPPED
> Task :core-storage:compileReleaseKotlin UP-TO-DATE
> Task :core-storage:javaPreCompileRelease UP-TO-DATE
> Task :core-storage:compileReleaseJavaWithJavac NO-SOURCE
> Task :core-storage:bundleLibCompileToJarRelease UP-TO-DATE
> Task :feature-map:kaptGenerateStubsReleaseKotlin UP-TO-DATE
> Task :feature-map:kaptReleaseKotlin SKIPPED
> Task :feature-map:compileReleaseKotlin UP-TO-DATE
> Task :feature-map:javaPreCompileRelease UP-TO-DATE
> Task :feature-map:compileReleaseJavaWithJavac NO-SOURCE
> Task :feature-map:bundleLibCompileToJarRelease UP-TO-DATE
> Task :feature-profile:kaptGenerateStubsReleaseKotlin UP-TO-DATE
> Task :feature-profile:kaptReleaseKotlin SKIPPED
> Task :feature-profile:compileReleaseKotlin UP-TO-DATE
> Task :feature-profile:javaPreCompileRelease UP-TO-DATE
> Task :feature-profile:compileReleaseJavaWithJavac NO-SOURCE
> Task :feature-profile:bundleLibCompileToJarRelease UP-TO-DATE
> Task :feature-qr:kaptGenerateStubsReleaseKotlin UP-TO-DATE
> Task :feature-qr:kaptReleaseKotlin SKIPPED
> Task :feature-qr:compileReleaseKotlin UP-TO-DATE
> Task :feature-qr:javaPreCompileRelease UP-TO-DATE
> Task :feature-qr:compileReleaseJavaWithJavac NO-SOURCE
> Task :feature-qr:bundleLibCompileToJarRelease UP-TO-DATE
> Task :feature-quiz:kaptGenerateStubsReleaseKotlin UP-TO-DATE
> Task :feature-quiz:kaptReleaseKotlin SKIPPED
> Task :feature-quiz:compileReleaseKotlin UP-TO-DATE
> Task :feature-quiz:javaPreCompileRelease UP-TO-DATE
> Task :feature-quiz:compileReleaseJavaWithJavac NO-SOURCE
> Task :feature-quiz:bundleLibCompileToJarRelease UP-TO-DATE
> Task :feature-training:kaptGenerateStubsReleaseKotlin UP-TO-DATE
> Task :feature-training:kaptReleaseKotlin SKIPPED
> Task :feature-training:compileReleaseKotlin UP-TO-DATE
> Task :feature-training:javaPreCompileRelease UP-TO-DATE
> Task :feature-training:compileReleaseJavaWithJavac NO-SOURCE
> Task :feature-training:bundleLibCompileToJarRelease UP-TO-DATE
> Task :app:kaptGenerateStubsReleaseKotlin UP-TO-DATE
> Task :app:kaptReleaseKotlin SKIPPED
> Task :app:compileReleaseKotlin UP-TO-DATE
> Task :app:javaPreCompileRelease UP-TO-DATE
> Task :app:compileReleaseJavaWithJavac NO-SOURCE
> Task :app:extractProguardFiles UP-TO-DATE
> Task :core-database:bundleLibRuntimeToJarRelease UP-TO-DATE
> Task :core-database:processReleaseJavaRes UP-TO-DATE
> Task :core-database:createFullJarRelease UP-TO-DATE
> Task :core-database:extractProguardFiles UP-TO-DATE
> Task :core-database:generateReleaseLintModel UP-TO-DATE
> Task :core-database:prepareLintJarForPublish UP-TO-DATE
> Task :core-mapping:bundleLibRuntimeToJarRelease UP-TO-DATE
> Task :core-mapping:processReleaseJavaRes NO-SOURCE
> Task :core-mapping:createFullJarRelease UP-TO-DATE
> Task :core-mapping:extractProguardFiles UP-TO-DATE
> Task :core-mapping:generateReleaseLintModel UP-TO-DATE
> Task :core-mapping:prepareLintJarForPublish UP-TO-DATE
> Task :core-navigation:bundleLibRuntimeToJarRelease UP-TO-DATE
> Task :core-navigation:processReleaseJavaRes UP-TO-DATE
> Task :core-navigation:createFullJarRelease UP-TO-DATE
> Task :core-navigation:extractProguardFiles UP-TO-DATE
> Task :core-navigation:generateReleaseLintModel UP-TO-DATE
> Task :core-navigation:prepareLintJarForPublish UP-TO-DATE
> Task :core-security:bundleLibRuntimeToJarRelease UP-TO-DATE
> Task :core-security:processReleaseJavaRes UP-TO-DATE
> Task :core-security:createFullJarRelease UP-TO-DATE
> Task :core-security:extractProguardFiles UP-TO-DATE
> Task :core-security:generateReleaseLintModel UP-TO-DATE
> Task :core-security:prepareLintJarForPublish UP-TO-DATE
> Task :core-storage:bundleLibRuntimeToJarRelease UP-TO-DATE
> Task :core-storage:processReleaseJavaRes UP-TO-DATE
> Task :core-storage:createFullJarRelease UP-TO-DATE
> Task :core-storage:extractProguardFiles UP-TO-DATE
> Task :core-storage:generateReleaseLintModel UP-TO-DATE
> Task :core-storage:prepareLintJarForPublish UP-TO-DATE
> Task :feature-map:bundleLibRuntimeToJarRelease UP-TO-DATE
> Task :feature-map:processReleaseJavaRes UP-TO-DATE
> Task :feature-map:createFullJarRelease UP-TO-DATE
> Task :feature-map:extractProguardFiles UP-TO-DATE
> Task :feature-map:generateReleaseLintModel UP-TO-DATE
> Task :feature-map:prepareLintJarForPublish UP-TO-DATE
> Task :feature-profile:bundleLibRuntimeToJarRelease UP-TO-DATE
> Task :feature-profile:processReleaseJavaRes UP-TO-DATE
> Task :feature-profile:createFullJarRelease UP-TO-DATE
> Task :feature-profile:extractProguardFiles UP-TO-DATE
> Task :feature-profile:generateReleaseLintModel UP-TO-DATE
> Task :feature-profile:prepareLintJarForPublish UP-TO-DATE
> Task :feature-qr:bundleLibRuntimeToJarRelease UP-TO-DATE
> Task :feature-qr:processReleaseJavaRes UP-TO-DATE
> Task :feature-qr:createFullJarRelease UP-TO-DATE
> Task :feature-qr:extractProguardFiles UP-TO-DATE
> Task :feature-qr:generateReleaseLintModel UP-TO-DATE
> Task :feature-qr:prepareLintJarForPublish UP-TO-DATE
> Task :feature-quiz:bundleLibRuntimeToJarRelease UP-TO-DATE
> Task :feature-quiz:processReleaseJavaRes UP-TO-DATE
> Task :feature-quiz:createFullJarRelease UP-TO-DATE
> Task :feature-quiz:extractProguardFiles UP-TO-DATE
> Task :feature-quiz:generateReleaseLintModel UP-TO-DATE
> Task :feature-quiz:prepareLintJarForPublish UP-TO-DATE
> Task :feature-training:bundleLibRuntimeToJarRelease UP-TO-DATE
> Task :feature-training:processReleaseJavaRes UP-TO-DATE
> Task :feature-training:createFullJarRelease UP-TO-DATE
> Task :feature-training:extractProguardFiles UP-TO-DATE
> Task :feature-training:generateReleaseLintModel UP-TO-DATE
> Task :feature-training:prepareLintJarForPublish UP-TO-DATE
> Task :app:generateReleaseLintVitalReportModel UP-TO-DATE
> Task :core-navigation:mergeReleaseJniLibFolders UP-TO-DATE
> Task :core-navigation:mergeReleaseNativeLibs NO-SOURCE
> Task :core-navigation:stripReleaseDebugSymbols NO-SOURCE
> Task :core-navigation:copyReleaseJniLibsProjectAndLocalJars UP-TO-DATE
> Task :core-navigation:extractDeepLinksForAarRelease UP-TO-DATE
> Task :core-navigation:extractReleaseAnnotations UP-TO-DATE
> Task :core-navigation:mergeReleaseGeneratedProguardFiles UP-TO-DATE
> Task :core-navigation:mergeReleaseConsumerProguardFiles UP-TO-DATE
> Task :core-navigation:mergeReleaseShaders UP-TO-DATE
> Task :core-navigation:compileReleaseShaders NO-SOURCE
> Task :core-navigation:generateReleaseAssets UP-TO-DATE
> Task :core-navigation:packageReleaseAssets UP-TO-DATE
> Task :core-navigation:prepareReleaseArtProfile UP-TO-DATE
> Task :core-navigation:mergeReleaseJavaResource UP-TO-DATE
> Task :core-navigation:syncReleaseLibJars UP-TO-DATE
> Task :core-navigation:bundleReleaseLocalLintAar UP-TO-DATE
> Task :feature-quiz:mergeReleaseJniLibFolders UP-TO-DATE
> Task :feature-quiz:mergeReleaseNativeLibs NO-SOURCE
> Task :feature-quiz:stripReleaseDebugSymbols NO-SOURCE
> Task :feature-quiz:copyReleaseJniLibsProjectAndLocalJars UP-TO-DATE
> Task :feature-quiz:extractDeepLinksForAarRelease UP-TO-DATE
> Task :feature-quiz:extractReleaseAnnotations UP-TO-DATE
> Task :feature-quiz:mergeReleaseGeneratedProguardFiles UP-TO-DATE
> Task :feature-quiz:mergeReleaseConsumerProguardFiles UP-TO-DATE
> Task :feature-quiz:mergeReleaseShaders UP-TO-DATE
> Task :feature-quiz:compileReleaseShaders NO-SOURCE
> Task :feature-quiz:generateReleaseAssets UP-TO-DATE
> Task :feature-quiz:packageReleaseAssets UP-TO-DATE
> Task :feature-quiz:prepareReleaseArtProfile UP-TO-DATE
> Task :feature-quiz:mergeReleaseJavaResource UP-TO-DATE
> Task :feature-quiz:syncReleaseLibJars UP-TO-DATE
> Task :feature-quiz:bundleReleaseLocalLintAar UP-TO-DATE
> Task :feature-map:mergeReleaseJniLibFolders UP-TO-DATE
> Task :feature-map:mergeReleaseNativeLibs NO-SOURCE
> Task :feature-map:stripReleaseDebugSymbols NO-SOURCE
> Task :feature-map:copyReleaseJniLibsProjectAndLocalJars UP-TO-DATE
> Task :feature-map:extractDeepLinksForAarRelease UP-TO-DATE
> Task :feature-map:extractReleaseAnnotations UP-TO-DATE
> Task :feature-map:mergeReleaseGeneratedProguardFiles UP-TO-DATE
> Task :feature-map:mergeReleaseConsumerProguardFiles UP-TO-DATE
> Task :feature-map:mergeReleaseShaders UP-TO-DATE
> Task :feature-map:compileReleaseShaders NO-SOURCE
> Task :feature-map:generateReleaseAssets UP-TO-DATE
> Task :feature-map:packageReleaseAssets UP-TO-DATE
> Task :feature-map:prepareReleaseArtProfile UP-TO-DATE
> Task :feature-map:mergeReleaseJavaResource UP-TO-DATE
> Task :feature-map:syncReleaseLibJars UP-TO-DATE
> Task :feature-map:bundleReleaseLocalLintAar UP-TO-DATE
> Task :feature-training:mergeReleaseJniLibFolders UP-TO-DATE
> Task :feature-training:mergeReleaseNativeLibs NO-SOURCE
> Task :feature-training:stripReleaseDebugSymbols NO-SOURCE
> Task :feature-training:copyReleaseJniLibsProjectAndLocalJars UP-TO-DATE
> Task :feature-training:extractDeepLinksForAarRelease UP-TO-DATE
> Task :feature-training:extractReleaseAnnotations UP-TO-DATE
> Task :feature-training:mergeReleaseGeneratedProguardFiles UP-TO-DATE
> Task :feature-training:mergeReleaseConsumerProguardFiles UP-TO-DATE
> Task :feature-training:mergeReleaseShaders UP-TO-DATE
> Task :feature-training:compileReleaseShaders NO-SOURCE
> Task :feature-training:generateReleaseAssets UP-TO-DATE
> Task :feature-training:packageReleaseAssets UP-TO-DATE
> Task :feature-training:prepareReleaseArtProfile UP-TO-DATE
> Task :feature-training:mergeReleaseJavaResource UP-TO-DATE
> Task :feature-training:syncReleaseLibJars UP-TO-DATE
> Task :feature-training:bundleReleaseLocalLintAar UP-TO-DATE
> Task :feature-qr:mergeReleaseJniLibFolders UP-TO-DATE
> Task :feature-qr:mergeReleaseNativeLibs NO-SOURCE
> Task :feature-qr:stripReleaseDebugSymbols NO-SOURCE
> Task :feature-qr:copyReleaseJniLibsProjectAndLocalJars UP-TO-DATE
> Task :feature-qr:extractDeepLinksForAarRelease UP-TO-DATE
> Task :feature-qr:extractReleaseAnnotations UP-TO-DATE
> Task :feature-qr:mergeReleaseGeneratedProguardFiles UP-TO-DATE
> Task :feature-qr:mergeReleaseConsumerProguardFiles UP-TO-DATE
> Task :feature-qr:mergeReleaseShaders UP-TO-DATE
> Task :feature-qr:compileReleaseShaders NO-SOURCE
> Task :feature-qr:generateReleaseAssets UP-TO-DATE
> Task :feature-qr:packageReleaseAssets UP-TO-DATE
> Task :feature-qr:prepareReleaseArtProfile UP-TO-DATE
> Task :feature-qr:mergeReleaseJavaResource UP-TO-DATE
> Task :feature-qr:syncReleaseLibJars UP-TO-DATE
> Task :feature-qr:bundleReleaseLocalLintAar UP-TO-DATE
> Task :feature-profile:mergeReleaseJniLibFolders UP-TO-DATE
> Task :feature-profile:mergeReleaseNativeLibs NO-SOURCE
> Task :feature-profile:stripReleaseDebugSymbols NO-SOURCE
> Task :feature-profile:copyReleaseJniLibsProjectAndLocalJars UP-TO-DATE
> Task :feature-profile:extractDeepLinksForAarRelease UP-TO-DATE
> Task :feature-profile:extractReleaseAnnotations UP-TO-DATE
> Task :feature-profile:mergeReleaseGeneratedProguardFiles UP-TO-DATE
> Task :feature-profile:mergeReleaseConsumerProguardFiles UP-TO-DATE
> Task :feature-profile:mergeReleaseShaders UP-TO-DATE
> Task :feature-profile:compileReleaseShaders NO-SOURCE
> Task :feature-profile:generateReleaseAssets UP-TO-DATE
> Task :feature-profile:packageReleaseAssets UP-TO-DATE
> Task :feature-profile:prepareReleaseArtProfile UP-TO-DATE
> Task :feature-profile:mergeReleaseJavaResource UP-TO-DATE
> Task :feature-profile:syncReleaseLibJars UP-TO-DATE
> Task :feature-profile:bundleReleaseLocalLintAar UP-TO-DATE
> Task :core-database:mergeReleaseJniLibFolders UP-TO-DATE
> Task :core-database:mergeReleaseNativeLibs NO-SOURCE
> Task :core-database:stripReleaseDebugSymbols NO-SOURCE
> Task :core-database:copyReleaseJniLibsProjectAndLocalJars UP-TO-DATE
> Task :core-database:extractDeepLinksForAarRelease UP-TO-DATE
> Task :core-database:extractReleaseAnnotations UP-TO-DATE
> Task :core-database:mergeReleaseGeneratedProguardFiles UP-TO-DATE
> Task :core-database:mergeReleaseConsumerProguardFiles UP-TO-DATE
> Task :core-database:mergeReleaseShaders UP-TO-DATE
> Task :core-database:compileReleaseShaders NO-SOURCE
> Task :core-database:generateReleaseAssets UP-TO-DATE
> Task :core-database:packageReleaseAssets UP-TO-DATE
> Task :core-database:prepareReleaseArtProfile UP-TO-DATE
> Task :core-database:mergeReleaseJavaResource UP-TO-DATE
> Task :core-database:syncReleaseLibJars UP-TO-DATE
> Task :core-database:bundleReleaseLocalLintAar UP-TO-DATE
> Task :core-storage:mergeReleaseJniLibFolders UP-TO-DATE
> Task :core-storage:mergeReleaseNativeLibs NO-SOURCE
> Task :core-storage:stripReleaseDebugSymbols NO-SOURCE
> Task :core-storage:copyReleaseJniLibsProjectAndLocalJars UP-TO-DATE
> Task :core-storage:extractDeepLinksForAarRelease UP-TO-DATE
> Task :core-storage:extractReleaseAnnotations UP-TO-DATE
> Task :core-storage:mergeReleaseGeneratedProguardFiles UP-TO-DATE
> Task :core-storage:mergeReleaseConsumerProguardFiles UP-TO-DATE
> Task :core-storage:mergeReleaseShaders UP-TO-DATE
> Task :core-storage:compileReleaseShaders NO-SOURCE
> Task :core-storage:generateReleaseAssets UP-TO-DATE
> Task :core-storage:packageReleaseAssets UP-TO-DATE
> Task :core-storage:prepareReleaseArtProfile UP-TO-DATE
> Task :core-storage:mergeReleaseJavaResource UP-TO-DATE
> Task :core-storage:syncReleaseLibJars UP-TO-DATE
> Task :core-storage:bundleReleaseLocalLintAar UP-TO-DATE
> Task :core-security:mergeReleaseJniLibFolders UP-TO-DATE
> Task :core-security:mergeReleaseNativeLibs NO-SOURCE
> Task :core-security:stripReleaseDebugSymbols NO-SOURCE
> Task :core-security:copyReleaseJniLibsProjectAndLocalJars UP-TO-DATE
> Task :core-security:extractDeepLinksForAarRelease UP-TO-DATE
> Task :core-security:extractReleaseAnnotations UP-TO-DATE
> Task :core-security:mergeReleaseGeneratedProguardFiles UP-TO-DATE
> Task :core-security:mergeReleaseConsumerProguardFiles UP-TO-DATE
> Task :core-security:mergeReleaseShaders UP-TO-DATE
> Task :core-security:compileReleaseShaders NO-SOURCE
> Task :core-security:generateReleaseAssets UP-TO-DATE
> Task :core-security:packageReleaseAssets UP-TO-DATE
> Task :core-security:prepareReleaseArtProfile UP-TO-DATE
> Task :core-security:mergeReleaseJavaResource UP-TO-DATE
> Task :core-security:syncReleaseLibJars UP-TO-DATE
> Task :core-security:bundleReleaseLocalLintAar UP-TO-DATE
> Task :core-mapping:mergeReleaseJniLibFolders UP-TO-DATE
> Task :core-mapping:mergeReleaseNativeLibs NO-SOURCE
> Task :core-mapping:stripReleaseDebugSymbols NO-SOURCE
> Task :core-mapping:copyReleaseJniLibsProjectAndLocalJars UP-TO-DATE
> Task :core-mapping:extractDeepLinksForAarRelease UP-TO-DATE
> Task :core-mapping:extractReleaseAnnotations UP-TO-DATE
> Task :core-mapping:mergeReleaseGeneratedProguardFiles UP-TO-DATE
> Task :core-mapping:mergeReleaseConsumerProguardFiles UP-TO-DATE
> Task :core-mapping:mergeReleaseShaders UP-TO-DATE
> Task :core-mapping:compileReleaseShaders NO-SOURCE
> Task :core-mapping:generateReleaseAssets UP-TO-DATE
> Task :core-mapping:packageReleaseAssets UP-TO-DATE
> Task :core-mapping:prepareReleaseArtProfile UP-TO-DATE
> Task :core-mapping:mergeReleaseJavaResource UP-TO-DATE
> Task :core-mapping:syncReleaseLibJars UP-TO-DATE
> Task :core-mapping:bundleReleaseLocalLintAar UP-TO-DATE
> Task :core-database:lintVitalAnalyzeRelease UP-TO-DATE
> Task :core-database:writeReleaseLintModelMetadata UP-TO-DATE
> Task :core-mapping:lintVitalAnalyzeRelease UP-TO-DATE
> Task :core-mapping:writeReleaseLintModelMetadata UP-TO-DATE
> Task :core-navigation:lintVitalAnalyzeRelease UP-TO-DATE
> Task :core-navigation:writeReleaseLintModelMetadata UP-TO-DATE
> Task :core-security:lintVitalAnalyzeRelease UP-TO-DATE
> Task :core-security:writeReleaseLintModelMetadata UP-TO-DATE
> Task :core-storage:lintVitalAnalyzeRelease UP-TO-DATE
> Task :core-storage:writeReleaseLintModelMetadata UP-TO-DATE
> Task :feature-map:lintVitalAnalyzeRelease UP-TO-DATE
> Task :feature-map:writeReleaseLintModelMetadata UP-TO-DATE
> Task :feature-profile:lintVitalAnalyzeRelease UP-TO-DATE
> Task :feature-profile:writeReleaseLintModelMetadata UP-TO-DATE
> Task :feature-qr:lintVitalAnalyzeRelease UP-TO-DATE
> Task :feature-qr:writeReleaseLintModelMetadata UP-TO-DATE
> Task :feature-quiz:lintVitalAnalyzeRelease UP-TO-DATE
> Task :feature-quiz:writeReleaseLintModelMetadata UP-TO-DATE
> Task :feature-training:lintVitalAnalyzeRelease UP-TO-DATE
> Task :feature-training:writeReleaseLintModelMetadata UP-TO-DATE
> Task :app:lintVitalAnalyzeRelease UP-TO-DATE
> Task :core-database:generateReleaseLintVitalModel UP-TO-DATE
> Task :core-mapping:generateReleaseLintVitalModel UP-TO-DATE
> Task :core-navigation:generateReleaseLintVitalModel UP-TO-DATE
> Task :core-security:generateReleaseLintVitalModel UP-TO-DATE
> Task :core-storage:generateReleaseLintVitalModel UP-TO-DATE
> Task :feature-map:generateReleaseLintVitalModel UP-TO-DATE
> Task :feature-profile:generateReleaseLintVitalModel UP-TO-DATE
> Task :feature-qr:generateReleaseLintVitalModel UP-TO-DATE
> Task :feature-quiz:generateReleaseLintVitalModel UP-TO-DATE
> Task :feature-training:generateReleaseLintVitalModel UP-TO-DATE
> Task :app:lintVitalReportRelease SKIPPED
> Task :app:lintVitalRelease SKIPPED
> Task :app:mergeReleaseJniLibFolders UP-TO-DATE
> Task :core-database:copyReleaseJniLibsProjectOnly UP-TO-DATE
> Task :core-mapping:copyReleaseJniLibsProjectOnly UP-TO-DATE
> Task :core-navigation:copyReleaseJniLibsProjectOnly UP-TO-DATE
> Task :core-security:copyReleaseJniLibsProjectOnly UP-TO-DATE
> Task :core-storage:copyReleaseJniLibsProjectOnly UP-TO-DATE
> Task :feature-map:copyReleaseJniLibsProjectOnly UP-TO-DATE
> Task :feature-profile:copyReleaseJniLibsProjectOnly UP-TO-DATE
> Task :feature-qr:copyReleaseJniLibsProjectOnly UP-TO-DATE
> Task :feature-quiz:copyReleaseJniLibsProjectOnly UP-TO-DATE
> Task :feature-training:copyReleaseJniLibsProjectOnly UP-TO-DATE
> Task :app:mergeReleaseNativeLibs UP-TO-DATE
> Task :app:stripReleaseDebugSymbols UP-TO-DATE
> Task :app:extractReleaseNativeSymbolTables UP-TO-DATE
> Task :app:mergeReleaseNativeDebugMetadata NO-SOURCE
> Task :core-navigation:exportReleaseConsumerProguardFiles UP-TO-DATE
> Task :feature-quiz:exportReleaseConsumerProguardFiles UP-TO-DATE
> Task :feature-map:exportReleaseConsumerProguardFiles UP-TO-DATE
> Task :feature-training:exportReleaseConsumerProguardFiles UP-TO-DATE
> Task :feature-qr:exportReleaseConsumerProguardFiles UP-TO-DATE
> Task :feature-profile:exportReleaseConsumerProguardFiles UP-TO-DATE
> Task :core-database:exportReleaseConsumerProguardFiles UP-TO-DATE
> Task :core-storage:exportReleaseConsumerProguardFiles UP-TO-DATE
> Task :core-security:exportReleaseConsumerProguardFiles UP-TO-DATE
> Task :core-mapping:exportReleaseConsumerProguardFiles UP-TO-DATE
> Task :app:checkReleaseDuplicateClasses UP-TO-DATE
> Task :app:mergeReleaseArtProfile UP-TO-DATE
> Task :app:expandReleaseArtProfileWildcards UP-TO-DATE
> Task :app:mergeReleaseGeneratedProguardFiles UP-TO-DATE
> Task :app:processReleaseJavaRes UP-TO-DATE
> Task :app:mergeReleaseJavaResource UP-TO-DATE
> Task :app:mergeReleaseStartupProfile UP-TO-DATE
> Task :app:minifyReleaseWithR8 UP-TO-DATE
> Task :app:compileReleaseArtProfile UP-TO-DATE
> Task :app:mergeReleaseShaders UP-TO-DATE
> Task :app:compileReleaseShaders NO-SOURCE
> Task :app:generateReleaseAssets UP-TO-DATE
> Task :app:mergeReleaseAssets UP-TO-DATE
> Task :app:compressReleaseAssets UP-TO-DATE
> Task :app:extractReleaseVersionControlInfo UP-TO-DATE
> Task :app:optimizeReleaseResources UP-TO-DATE
> Task :app:collectReleaseDependencies UP-TO-DATE
> Task :app:sdkReleaseDependencyData UP-TO-DATE
> Task :app:writeReleaseAppMetadata UP-TO-DATE
> Task :app:writeReleaseSigningConfigVersions UP-TO-DATE
> Task :app:packageRelease UP-TO-DATE
> Task :app:createReleaseApkListingFileRedirect UP-TO-DATE
> Task :app:assembleRelease UP-TO-DATE
> Task :app:assemble UP-TO-DATE
> Task :app:detekt UP-TO-DATE
> Task :app:loadKtlintReporters UP-TO-DATE
> Task :app:runKtlintCheckOverAndroidTestDebugSourceSet NO-SOURCE
> Task :app:ktlintAndroidTestDebugSourceSetCheck SKIPPED
> Task :app:runKtlintCheckOverAndroidTestReleaseSourceSet NO-SOURCE
> Task :app:ktlintAndroidTestReleaseSourceSetCheck SKIPPED
> Task :app:runKtlintCheckOverAndroidTestSourceSet NO-SOURCE
> Task :app:ktlintAndroidTestSourceSetCheck SKIPPED
> Task :app:runKtlintCheckOverDebugSourceSet NO-SOURCE
> Task :app:ktlintDebugSourceSetCheck SKIPPED
> Task :app:runKtlintCheckOverKotlinScripts UP-TO-DATE
> Task :app:ktlintKotlinScriptCheck UP-TO-DATE
> Task :app:runKtlintCheckOverMainSourceSet UP-TO-DATE
> Task :app:ktlintMainSourceSetCheck UP-TO-DATE
> Task :app:runKtlintCheckOverReleaseSourceSet NO-SOURCE
> Task :app:ktlintReleaseSourceSetCheck SKIPPED
> Task :app:runKtlintCheckOverTestDebugSourceSet NO-SOURCE
> Task :app:ktlintTestDebugSourceSetCheck SKIPPED
> Task :app:runKtlintCheckOverTestFixturesDebugSourceSet NO-SOURCE
> Task :app:ktlintTestFixturesDebugSourceSetCheck SKIPPED
> Task :app:runKtlintCheckOverTestFixturesReleaseSourceSet NO-SOURCE
> Task :app:ktlintTestFixturesReleaseSourceSetCheck SKIPPED
> Task :app:runKtlintCheckOverTestFixturesSourceSet NO-SOURCE
> Task :app:ktlintTestFixturesSourceSetCheck SKIPPED
> Task :app:runKtlintCheckOverTestReleaseSourceSet NO-SOURCE
> Task :app:ktlintTestReleaseSourceSetCheck SKIPPED
> Task :app:runKtlintCheckOverTestSourceSet NO-SOURCE
> Task :app:ktlintTestSourceSetCheck SKIPPED
> Task :core-navigation:stripDebugDebugSymbols NO-SOURCE
> Task :core-navigation:copyDebugJniLibsProjectAndLocalJars UP-TO-DATE
> Task :core-navigation:extractDebugAnnotations UP-TO-DATE
> Task :core-navigation:extractDeepLinksForAarDebug UP-TO-DATE
> Task :core-navigation:mergeDebugGeneratedProguardFiles UP-TO-DATE
> Task :core-navigation:mergeDebugConsumerProguardFiles UP-TO-DATE
> Task :core-navigation:prepareDebugArtProfile UP-TO-DATE
> Task :core-navigation:mergeDebugJavaResource UP-TO-DATE
> Task :core-navigation:syncDebugLibJars UP-TO-DATE
> Task :core-navigation:bundleDebugLocalLintAar UP-TO-DATE
> Task :core-database:stripDebugDebugSymbols NO-SOURCE
> Task :core-database:copyDebugJniLibsProjectAndLocalJars UP-TO-DATE
> Task :core-database:extractDebugAnnotations UP-TO-DATE
> Task :core-database:extractDeepLinksForAarDebug UP-TO-DATE
> Task :core-database:mergeDebugGeneratedProguardFiles UP-TO-DATE
> Task :core-database:mergeDebugConsumerProguardFiles UP-TO-DATE
> Task :core-database:prepareDebugArtProfile UP-TO-DATE
> Task :core-database:mergeDebugJavaResource UP-TO-DATE
> Task :core-database:syncDebugLibJars UP-TO-DATE
> Task :core-database:bundleDebugLocalLintAar UP-TO-DATE
> Task :core-storage:stripDebugDebugSymbols NO-SOURCE
> Task :core-storage:copyDebugJniLibsProjectAndLocalJars UP-TO-DATE
> Task :core-storage:extractDebugAnnotations UP-TO-DATE
> Task :core-storage:extractDeepLinksForAarDebug UP-TO-DATE
> Task :core-storage:mergeDebugGeneratedProguardFiles UP-TO-DATE
> Task :core-storage:mergeDebugConsumerProguardFiles UP-TO-DATE
> Task :core-storage:prepareDebugArtProfile UP-TO-DATE
> Task :core-storage:mergeDebugJavaResource UP-TO-DATE
> Task :core-storage:syncDebugLibJars UP-TO-DATE
> Task :core-storage:bundleDebugLocalLintAar UP-TO-DATE
> Task :core-security:stripDebugDebugSymbols NO-SOURCE
> Task :core-security:copyDebugJniLibsProjectAndLocalJars UP-TO-DATE
> Task :core-security:extractDebugAnnotations UP-TO-DATE
> Task :core-security:extractDeepLinksForAarDebug UP-TO-DATE
> Task :core-security:mergeDebugGeneratedProguardFiles UP-TO-DATE
> Task :core-security:mergeDebugConsumerProguardFiles UP-TO-DATE
> Task :core-security:prepareDebugArtProfile UP-TO-DATE
> Task :core-security:mergeDebugJavaResource UP-TO-DATE
> Task :core-security:syncDebugLibJars UP-TO-DATE
> Task :core-security:bundleDebugLocalLintAar UP-TO-DATE
> Task :core-mapping:stripDebugDebugSymbols NO-SOURCE
> Task :core-mapping:copyDebugJniLibsProjectAndLocalJars UP-TO-DATE
> Task :core-mapping:extractDebugAnnotations UP-TO-DATE
> Task :core-mapping:extractDeepLinksForAarDebug UP-TO-DATE
> Task :core-mapping:mergeDebugGeneratedProguardFiles UP-TO-DATE
> Task :core-mapping:mergeDebugConsumerProguardFiles UP-TO-DATE
> Task :core-mapping:prepareDebugArtProfile UP-TO-DATE
> Task :core-mapping:mergeDebugJavaResource UP-TO-DATE
> Task :core-mapping:syncDebugLibJars UP-TO-DATE
> Task :core-mapping:bundleDebugLocalLintAar UP-TO-DATE
> Task :feature-quiz:stripDebugDebugSymbols NO-SOURCE
> Task :feature-quiz:copyDebugJniLibsProjectAndLocalJars UP-TO-DATE
> Task :feature-quiz:extractDebugAnnotations UP-TO-DATE
> Task :feature-quiz:extractDeepLinksForAarDebug UP-TO-DATE
> Task :feature-quiz:mergeDebugGeneratedProguardFiles UP-TO-DATE
> Task :feature-quiz:mergeDebugConsumerProguardFiles UP-TO-DATE
> Task :feature-quiz:prepareDebugArtProfile UP-TO-DATE
> Task :feature-quiz:mergeDebugJavaResource UP-TO-DATE
> Task :feature-quiz:syncDebugLibJars UP-TO-DATE
> Task :feature-quiz:bundleDebugLocalLintAar UP-TO-DATE
> Task :feature-map:stripDebugDebugSymbols NO-SOURCE
> Task :feature-map:copyDebugJniLibsProjectAndLocalJars UP-TO-DATE
> Task :feature-map:extractDebugAnnotations UP-TO-DATE
> Task :feature-map:extractDeepLinksForAarDebug UP-TO-DATE
> Task :feature-map:mergeDebugGeneratedProguardFiles UP-TO-DATE
> Task :feature-map:mergeDebugConsumerProguardFiles UP-TO-DATE
> Task :feature-map:prepareDebugArtProfile UP-TO-DATE
> Task :feature-map:mergeDebugJavaResource UP-TO-DATE
> Task :feature-map:syncDebugLibJars UP-TO-DATE
> Task :feature-map:bundleDebugLocalLintAar UP-TO-DATE
> Task :feature-training:stripDebugDebugSymbols NO-SOURCE
> Task :feature-training:copyDebugJniLibsProjectAndLocalJars UP-TO-DATE
> Task :feature-training:extractDebugAnnotations UP-TO-DATE
> Task :feature-training:extractDeepLinksForAarDebug UP-TO-DATE
> Task :feature-training:mergeDebugGeneratedProguardFiles UP-TO-DATE
> Task :feature-training:mergeDebugConsumerProguardFiles UP-TO-DATE
> Task :feature-training:prepareDebugArtProfile UP-TO-DATE
> Task :feature-training:mergeDebugJavaResource UP-TO-DATE
> Task :feature-training:syncDebugLibJars UP-TO-DATE
> Task :feature-training:bundleDebugLocalLintAar UP-TO-DATE
> Task :feature-qr:stripDebugDebugSymbols NO-SOURCE
> Task :feature-qr:copyDebugJniLibsProjectAndLocalJars UP-TO-DATE
> Task :feature-qr:extractDebugAnnotations UP-TO-DATE
> Task :feature-qr:extractDeepLinksForAarDebug UP-TO-DATE
> Task :feature-qr:mergeDebugGeneratedProguardFiles UP-TO-DATE
> Task :feature-qr:mergeDebugConsumerProguardFiles UP-TO-DATE
> Task :feature-qr:prepareDebugArtProfile UP-TO-DATE
> Task :feature-qr:mergeDebugJavaResource UP-TO-DATE
> Task :feature-qr:syncDebugLibJars UP-TO-DATE
> Task :feature-qr:bundleDebugLocalLintAar UP-TO-DATE
> Task :feature-profile:stripDebugDebugSymbols NO-SOURCE
> Task :feature-profile:copyDebugJniLibsProjectAndLocalJars UP-TO-DATE
> Task :feature-profile:extractDebugAnnotations UP-TO-DATE
> Task :feature-profile:extractDeepLinksForAarDebug UP-TO-DATE
> Task :feature-profile:mergeDebugGeneratedProguardFiles UP-TO-DATE
> Task :feature-profile:mergeDebugConsumerProguardFiles UP-TO-DATE
> Task :feature-profile:prepareDebugArtProfile UP-TO-DATE
> Task :feature-profile:mergeDebugJavaResource UP-TO-DATE
> Task :feature-profile:syncDebugLibJars UP-TO-DATE
> Task :feature-profile:bundleDebugLocalLintAar UP-TO-DATE
> Task :app:bundleDebugClassesToCompileJar UP-TO-DATE
> Task :app:preDebugAndroidTestBuild SKIPPED
> Task :app:generateDebugAndroidTestResValues UP-TO-DATE
> Task :core-database:createFullJarDebug UP-TO-DATE
> Task :core-database:writeDebugLintModelMetadata UP-TO-DATE
> Task :core-mapping:createFullJarDebug UP-TO-DATE
> Task :core-mapping:writeDebugLintModelMetadata UP-TO-DATE
> Task :core-navigation:createFullJarDebug UP-TO-DATE
> Task :core-navigation:writeDebugLintModelMetadata UP-TO-DATE
> Task :core-security:createFullJarDebug UP-TO-DATE
> Task :core-security:writeDebugLintModelMetadata UP-TO-DATE
> Task :core-storage:createFullJarDebug UP-TO-DATE
> Task :core-storage:writeDebugLintModelMetadata UP-TO-DATE
> Task :feature-map:createFullJarDebug UP-TO-DATE
> Task :feature-map:writeDebugLintModelMetadata UP-TO-DATE
> Task :feature-profile:createFullJarDebug UP-TO-DATE
> Task :feature-profile:writeDebugLintModelMetadata UP-TO-DATE
> Task :feature-qr:createFullJarDebug UP-TO-DATE
> Task :feature-qr:writeDebugLintModelMetadata UP-TO-DATE
> Task :feature-quiz:createFullJarDebug UP-TO-DATE
> Task :feature-quiz:writeDebugLintModelMetadata UP-TO-DATE
> Task :feature-training:createFullJarDebug UP-TO-DATE
> Task :feature-training:writeDebugLintModelMetadata UP-TO-DATE
> Task :app:generateDebugAndroidTestLintModel UP-TO-DATE
> Task :core-database:generateDebugLintModel UP-TO-DATE
> Task :core-mapping:generateDebugLintModel UP-TO-DATE
> Task :core-navigation:generateDebugLintModel UP-TO-DATE
> Task :core-security:generateDebugLintModel UP-TO-DATE
> Task :core-storage:generateDebugLintModel UP-TO-DATE
> Task :feature-map:generateDebugLintModel UP-TO-DATE
> Task :feature-profile:generateDebugLintModel UP-TO-DATE
> Task :feature-qr:generateDebugLintModel UP-TO-DATE
> Task :feature-quiz:generateDebugLintModel UP-TO-DATE
> Task :feature-training:generateDebugLintModel UP-TO-DATE
> Task :app:generateDebugLintReportModel UP-TO-DATE
> Task :app:preDebugUnitTestBuild UP-TO-DATE
> Task :app:generateDebugUnitTestLintModel UP-TO-DATE
> Task :core-database:lintAnalyzeDebug UP-TO-DATE
> Task :core-mapping:lintAnalyzeDebug UP-TO-DATE
> Task :core-navigation:lintAnalyzeDebug UP-TO-DATE
> Task :core-security:lintAnalyzeDebug UP-TO-DATE
> Task :core-storage:lintAnalyzeDebug UP-TO-DATE
> Task :feature-map:lintAnalyzeDebug UP-TO-DATE
> Task :feature-profile:lintAnalyzeDebug UP-TO-DATE
> Task :feature-qr:lintAnalyzeDebug UP-TO-DATE
> Task :feature-quiz:lintAnalyzeDebug UP-TO-DATE
> Task :feature-training:lintAnalyzeDebug UP-TO-DATE
> Task :app:lintAnalyzeDebug UP-TO-DATE
> Task :app:lintAnalyzeDebugAndroidTest UP-TO-DATE
> Task :app:lintAnalyzeDebugUnitTest UP-TO-DATE
> Task :core-database:preDebugAndroidTestBuild UP-TO-DATE
> Task :core-database:generateDebugAndroidTestResValues UP-TO-DATE
> Task :core-database:generateDebugAndroidTestLintModel UP-TO-DATE
> Task :core-database:preDebugUnitTestBuild UP-TO-DATE
> Task :core-database:generateDebugUnitTestLintModel UP-TO-DATE
> Task :core-database:lintAnalyzeDebugAndroidTest UP-TO-DATE
> Task :core-database:lintAnalyzeDebugUnitTest UP-TO-DATE
> Task :core-mapping:preDebugAndroidTestBuild UP-TO-DATE
> Task :core-mapping:generateDebugAndroidTestResValues UP-TO-DATE
> Task :core-mapping:generateDebugAndroidTestLintModel UP-TO-DATE
> Task :core-mapping:preDebugUnitTestBuild UP-TO-DATE
> Task :core-mapping:generateDebugUnitTestLintModel UP-TO-DATE
> Task :core-mapping:lintAnalyzeDebugAndroidTest UP-TO-DATE
> Task :core-mapping:lintAnalyzeDebugUnitTest UP-TO-DATE
> Task :core-navigation:preDebugAndroidTestBuild UP-TO-DATE
> Task :core-navigation:generateDebugAndroidTestResValues UP-TO-DATE
> Task :core-navigation:generateDebugAndroidTestLintModel UP-TO-DATE
> Task :core-navigation:preDebugUnitTestBuild UP-TO-DATE
> Task :core-navigation:generateDebugUnitTestLintModel UP-TO-DATE
> Task :core-navigation:lintAnalyzeDebugAndroidTest UP-TO-DATE
> Task :core-navigation:lintAnalyzeDebugUnitTest UP-TO-DATE
> Task :core-security:preDebugAndroidTestBuild UP-TO-DATE
> Task :core-security:generateDebugAndroidTestResValues UP-TO-DATE
> Task :core-security:generateDebugAndroidTestLintModel UP-TO-DATE
> Task :core-security:preDebugUnitTestBuild UP-TO-DATE
> Task :core-security:generateDebugUnitTestLintModel UP-TO-DATE
> Task :core-security:lintAnalyzeDebugAndroidTest UP-TO-DATE
> Task :core-security:lintAnalyzeDebugUnitTest UP-TO-DATE
> Task :core-storage:preDebugAndroidTestBuild UP-TO-DATE
> Task :core-storage:generateDebugAndroidTestResValues UP-TO-DATE
> Task :core-storage:generateDebugAndroidTestLintModel UP-TO-DATE
> Task :core-storage:preDebugUnitTestBuild UP-TO-DATE
> Task :core-storage:generateDebugUnitTestLintModel UP-TO-DATE
> Task :core-storage:lintAnalyzeDebugAndroidTest UP-TO-DATE
> Task :core-storage:lintAnalyzeDebugUnitTest UP-TO-DATE
> Task :feature-map:preDebugAndroidTestBuild UP-TO-DATE
> Task :feature-map:generateDebugAndroidTestResValues UP-TO-DATE
> Task :feature-map:generateDebugAndroidTestLintModel UP-TO-DATE
> Task :feature-map:preDebugUnitTestBuild UP-TO-DATE
> Task :feature-map:generateDebugUnitTestLintModel UP-TO-DATE
> Task :feature-map:lintAnalyzeDebugAndroidTest UP-TO-DATE
> Task :feature-map:lintAnalyzeDebugUnitTest UP-TO-DATE
> Task :feature-profile:preDebugAndroidTestBuild UP-TO-DATE
> Task :feature-profile:generateDebugAndroidTestResValues UP-TO-DATE
> Task :feature-profile:generateDebugAndroidTestLintModel UP-TO-DATE
> Task :feature-profile:preDebugUnitTestBuild UP-TO-DATE
> Task :feature-profile:generateDebugUnitTestLintModel UP-TO-DATE
> Task :feature-profile:lintAnalyzeDebugAndroidTest UP-TO-DATE
> Task :feature-profile:lintAnalyzeDebugUnitTest UP-TO-DATE
> Task :feature-qr:preDebugAndroidTestBuild UP-TO-DATE
> Task :feature-qr:generateDebugAndroidTestResValues UP-TO-DATE
> Task :feature-qr:generateDebugAndroidTestLintModel UP-TO-DATE
> Task :feature-qr:preDebugUnitTestBuild UP-TO-DATE
> Task :feature-qr:generateDebugUnitTestLintModel UP-TO-DATE
> Task :feature-qr:lintAnalyzeDebugAndroidTest UP-TO-DATE
> Task :feature-qr:lintAnalyzeDebugUnitTest UP-TO-DATE
> Task :feature-quiz:preDebugAndroidTestBuild UP-TO-DATE
> Task :feature-quiz:generateDebugAndroidTestResValues UP-TO-DATE
> Task :feature-quiz:generateDebugAndroidTestLintModel UP-TO-DATE
> Task :feature-quiz:preDebugUnitTestBuild UP-TO-DATE
> Task :feature-quiz:generateDebugUnitTestLintModel UP-TO-DATE
> Task :feature-quiz:lintAnalyzeDebugAndroidTest UP-TO-DATE
> Task :feature-quiz:lintAnalyzeDebugUnitTest UP-TO-DATE
> Task :feature-training:preDebugAndroidTestBuild UP-TO-DATE
> Task :feature-training:generateDebugAndroidTestResValues UP-TO-DATE
> Task :feature-training:generateDebugAndroidTestLintModel UP-TO-DATE
> Task :feature-training:preDebugUnitTestBuild UP-TO-DATE
> Task :feature-training:generateDebugUnitTestLintModel UP-TO-DATE
> Task :feature-training:lintAnalyzeDebugAndroidTest UP-TO-DATE
> Task :feature-training:lintAnalyzeDebugUnitTest UP-TO-DATE
> Task :app:lintReportDebug UP-TO-DATE
> Task :app:lintDebug
> Task :app:lint
> Task :app:bundleDebugClassesToRuntimeJar UP-TO-DATE
> Task :app:kaptGenerateStubsDebugUnitTestKotlin UP-TO-DATE
> Task :app:kaptDebugUnitTestKotlin SKIPPED
> Task :app:compileDebugUnitTestKotlin NO-SOURCE
> Task :app:javaPreCompileDebugUnitTest UP-TO-DATE
> Task :app:compileDebugUnitTestJavaWithJavac NO-SOURCE
> Task :app:processDebugUnitTestJavaRes NO-SOURCE
> Task :app:testDebugUnitTest NO-SOURCE
> Task :app:bundleReleaseClassesToRuntimeJar UP-TO-DATE
> Task :app:bundleReleaseClassesToCompileJar UP-TO-DATE
> Task :app:kaptGenerateStubsReleaseUnitTestKotlin UP-TO-DATE
> Task :app:kaptReleaseUnitTestKotlin SKIPPED
> Task :app:compileReleaseUnitTestKotlin NO-SOURCE
> Task :app:preReleaseUnitTestBuild UP-TO-DATE
> Task :app:javaPreCompileReleaseUnitTest UP-TO-DATE
> Task :app:compileReleaseUnitTestJavaWithJavac NO-SOURCE
> Task :app:processReleaseUnitTestJavaRes NO-SOURCE
> Task :app:testReleaseUnitTest NO-SOURCE
> Task :app:test UP-TO-DATE
> Task :app:check
> Task :app:build
> Task :core-database:bundleDebugAar UP-TO-DATE
> Task :core-database:assembleDebug UP-TO-DATE
> Task :core-database:bundleReleaseAar UP-TO-DATE
> Task :core-database:mapReleaseSourceSetPaths UP-TO-DATE
> Task :core-database:mergeReleaseResources UP-TO-DATE
> Task :core-database:verifyReleaseResources UP-TO-DATE
> Task :core-database:assembleRelease UP-TO-DATE
> Task :core-database:assemble UP-TO-DATE
> Task :core-database:generateDebugLintReportModel UP-TO-DATE
> Task :core-database:lintReportDebug UP-TO-DATE
> Task :core-database:lintDebug
> Task :core-database:lint
> Task :core-database:generateDebugUnitTestStubRFile UP-TO-DATE
> Task :core-database:kspDebugUnitTestKotlin NO-SOURCE
> Task :core-database:kaptGenerateStubsDebugUnitTestKotlin UP-TO-DATE
> Task :core-database:kaptDebugUnitTestKotlin SKIPPED
> Task :core-database:compileDebugUnitTestKotlin NO-SOURCE
> Task :core-database:javaPreCompileDebugUnitTest UP-TO-DATE
> Task :core-database:compileDebugUnitTestJavaWithJavac NO-SOURCE
> Task :core-database:processDebugUnitTestJavaRes NO-SOURCE
> Task :core-database:testDebugUnitTest NO-SOURCE
> Task :core-database:preReleaseUnitTestBuild UP-TO-DATE
> Task :core-database:generateReleaseUnitTestStubRFile UP-TO-DATE
> Task :core-database:kspReleaseUnitTestKotlin NO-SOURCE
> Task :core-database:kaptGenerateStubsReleaseUnitTestKotlin UP-TO-DATE
> Task :core-database:kaptReleaseUnitTestKotlin SKIPPED
> Task :core-database:compileReleaseUnitTestKotlin NO-SOURCE
> Task :core-database:javaPreCompileReleaseUnitTest UP-TO-DATE
> Task :core-database:compileReleaseUnitTestJavaWithJavac NO-SOURCE
> Task :core-database:processReleaseUnitTestJavaRes NO-SOURCE
> Task :core-database:testReleaseUnitTest NO-SOURCE
> Task :core-database:test UP-TO-DATE
> Task :core-database:check
> Task :core-database:build
> Task :core-mapping:bundleDebugAar UP-TO-DATE
> Task :core-mapping:assembleDebug UP-TO-DATE
> Task :core-mapping:bundleReleaseAar UP-TO-DATE
> Task :core-mapping:mapReleaseSourceSetPaths UP-TO-DATE
> Task :core-mapping:mergeReleaseResources UP-TO-DATE
> Task :core-mapping:verifyReleaseResources UP-TO-DATE
> Task :core-mapping:assembleRelease UP-TO-DATE
> Task :core-mapping:assemble UP-TO-DATE
> Task :core-mapping:generateDebugLintReportModel UP-TO-DATE
> Task :core-mapping:lintReportDebug UP-TO-DATE
> Task :core-mapping:lintDebug
> Task :core-mapping:lint
> Task :core-mapping:generateDebugUnitTestStubRFile UP-TO-DATE
> Task :core-mapping:kaptGenerateStubsDebugUnitTestKotlin UP-TO-DATE
> Task :core-mapping:kaptDebugUnitTestKotlin SKIPPED
> Task :core-mapping:compileDebugUnitTestKotlin NO-SOURCE
> Task :core-mapping:javaPreCompileDebugUnitTest UP-TO-DATE
> Task :core-mapping:compileDebugUnitTestJavaWithJavac NO-SOURCE
> Task :core-mapping:processDebugUnitTestJavaRes NO-SOURCE
> Task :core-mapping:testDebugUnitTest NO-SOURCE
> Task :core-mapping:preReleaseUnitTestBuild UP-TO-DATE
> Task :core-mapping:generateReleaseUnitTestStubRFile UP-TO-DATE
> Task :core-mapping:kaptGenerateStubsReleaseUnitTestKotlin UP-TO-DATE
> Task :core-mapping:kaptReleaseUnitTestKotlin SKIPPED
> Task :core-mapping:compileReleaseUnitTestKotlin NO-SOURCE
> Task :core-mapping:javaPreCompileReleaseUnitTest UP-TO-DATE
> Task :core-mapping:compileReleaseUnitTestJavaWithJavac NO-SOURCE
> Task :core-mapping:processReleaseUnitTestJavaRes NO-SOURCE
> Task :core-mapping:testReleaseUnitTest NO-SOURCE
> Task :core-mapping:test UP-TO-DATE
> Task :core-mapping:check
> Task :core-mapping:build
> Task :core-navigation:bundleDebugAar UP-TO-DATE
> Task :core-navigation:assembleDebug UP-TO-DATE
> Task :core-navigation:bundleReleaseAar UP-TO-DATE
> Task :core-navigation:mapReleaseSourceSetPaths UP-TO-DATE
> Task :core-navigation:mergeReleaseResources UP-TO-DATE
> Task :core-navigation:verifyReleaseResources UP-TO-DATE
> Task :core-navigation:assembleRelease UP-TO-DATE
> Task :core-navigation:assemble UP-TO-DATE
> Task :core-navigation:generateDebugLintReportModel UP-TO-DATE
> Task :core-navigation:lintReportDebug UP-TO-DATE
> Task :core-navigation:lintDebug
> Task :core-navigation:lint
> Task :core-navigation:generateDebugUnitTestStubRFile UP-TO-DATE
> Task :core-navigation:kaptGenerateStubsDebugUnitTestKotlin UP-TO-DATE
> Task :core-navigation:kaptDebugUnitTestKotlin SKIPPED
> Task :core-navigation:compileDebugUnitTestKotlin NO-SOURCE
> Task :core-navigation:javaPreCompileDebugUnitTest UP-TO-DATE
> Task :core-navigation:compileDebugUnitTestJavaWithJavac NO-SOURCE
> Task :core-navigation:processDebugUnitTestJavaRes NO-SOURCE
> Task :core-navigation:testDebugUnitTest NO-SOURCE
> Task :core-navigation:preReleaseUnitTestBuild UP-TO-DATE
> Task :core-navigation:generateReleaseUnitTestStubRFile UP-TO-DATE
> Task :core-navigation:kaptGenerateStubsReleaseUnitTestKotlin UP-TO-DATE
> Task :core-navigation:kaptReleaseUnitTestKotlin SKIPPED
> Task :core-navigation:compileReleaseUnitTestKotlin NO-SOURCE
> Task :core-navigation:javaPreCompileReleaseUnitTest UP-TO-DATE
> Task :core-navigation:compileReleaseUnitTestJavaWithJavac NO-SOURCE
> Task :core-navigation:processReleaseUnitTestJavaRes NO-SOURCE
> Task :core-navigation:testReleaseUnitTest NO-SOURCE
> Task :core-navigation:test UP-TO-DATE
> Task :core-navigation:check
> Task :core-navigation:build
> Task :core-security:bundleDebugAar UP-TO-DATE
> Task :core-security:assembleDebug UP-TO-DATE
> Task :core-security:bundleReleaseAar UP-TO-DATE
> Task :core-security:mapReleaseSourceSetPaths UP-TO-DATE
> Task :core-security:mergeReleaseResources UP-TO-DATE
> Task :core-security:verifyReleaseResources UP-TO-DATE
> Task :core-security:assembleRelease UP-TO-DATE
> Task :core-security:assemble UP-TO-DATE
> Task :core-security:generateDebugLintReportModel UP-TO-DATE
> Task :core-security:lintReportDebug UP-TO-DATE
> Task :core-security:lintDebug
> Task :core-security:lint
> Task :core-security:generateDebugUnitTestStubRFile UP-TO-DATE
> Task :core-security:kaptGenerateStubsDebugUnitTestKotlin UP-TO-DATE
> Task :core-security:kaptDebugUnitTestKotlin SKIPPED
> Task :core-security:compileDebugUnitTestKotlin NO-SOURCE
> Task :core-security:javaPreCompileDebugUnitTest UP-TO-DATE
> Task :core-security:compileDebugUnitTestJavaWithJavac NO-SOURCE
> Task :core-security:processDebugUnitTestJavaRes NO-SOURCE
> Task :core-security:testDebugUnitTest NO-SOURCE
> Task :core-security:preReleaseUnitTestBuild UP-TO-DATE
> Task :core-security:generateReleaseUnitTestStubRFile UP-TO-DATE
> Task :core-security:kaptGenerateStubsReleaseUnitTestKotlin UP-TO-DATE
> Task :core-security:kaptReleaseUnitTestKotlin SKIPPED
> Task :core-security:compileReleaseUnitTestKotlin NO-SOURCE
> Task :core-security:javaPreCompileReleaseUnitTest UP-TO-DATE
> Task :core-security:compileReleaseUnitTestJavaWithJavac NO-SOURCE
> Task :core-security:processReleaseUnitTestJavaRes NO-SOURCE
> Task :core-security:testReleaseUnitTest NO-SOURCE
> Task :core-security:test UP-TO-DATE
> Task :core-security:check
> Task :core-security:build
> Task :core-storage:bundleDebugAar UP-TO-DATE
> Task :core-storage:assembleDebug UP-TO-DATE
> Task :core-storage:bundleReleaseAar UP-TO-DATE
> Task :core-storage:mapReleaseSourceSetPaths UP-TO-DATE
> Task :core-storage:mergeReleaseResources UP-TO-DATE
> Task :core-storage:verifyReleaseResources UP-TO-DATE
> Task :core-storage:assembleRelease UP-TO-DATE
> Task :core-storage:assemble UP-TO-DATE
> Task :core-storage:generateDebugLintReportModel UP-TO-DATE
> Task :core-storage:lintReportDebug UP-TO-DATE
> Task :core-storage:lintDebug
> Task :core-storage:lint
> Task :core-storage:generateDebugUnitTestStubRFile UP-TO-DATE
> Task :core-storage:kaptGenerateStubsDebugUnitTestKotlin UP-TO-DATE
> Task :core-storage:kaptDebugUnitTestKotlin SKIPPED
> Task :core-storage:compileDebugUnitTestKotlin NO-SOURCE
> Task :core-storage:javaPreCompileDebugUnitTest UP-TO-DATE
> Task :core-storage:compileDebugUnitTestJavaWithJavac NO-SOURCE
> Task :core-storage:processDebugUnitTestJavaRes NO-SOURCE
> Task :core-storage:testDebugUnitTest NO-SOURCE
> Task :core-storage:preReleaseUnitTestBuild UP-TO-DATE
> Task :core-storage:generateReleaseUnitTestStubRFile UP-TO-DATE
> Task :core-storage:kaptGenerateStubsReleaseUnitTestKotlin UP-TO-DATE
> Task :core-storage:kaptReleaseUnitTestKotlin SKIPPED
> Task :core-storage:compileReleaseUnitTestKotlin NO-SOURCE
> Task :core-storage:javaPreCompileReleaseUnitTest UP-TO-DATE
> Task :core-storage:compileReleaseUnitTestJavaWithJavac NO-SOURCE
> Task :core-storage:processReleaseUnitTestJavaRes NO-SOURCE
> Task :core-storage:testReleaseUnitTest NO-SOURCE
> Task :core-storage:test UP-TO-DATE
> Task :core-storage:check
> Task :core-storage:build
> Task :feature-map:bundleDebugAar UP-TO-DATE
> Task :feature-map:assembleDebug UP-TO-DATE
> Task :feature-map:bundleReleaseAar UP-TO-DATE
> Task :feature-map:mapReleaseSourceSetPaths UP-TO-DATE
> Task :feature-map:mergeReleaseResources UP-TO-DATE
> Task :feature-map:verifyReleaseResources UP-TO-DATE
> Task :feature-map:assembleRelease UP-TO-DATE
> Task :feature-map:assemble UP-TO-DATE
> Task :feature-map:generateDebugLintReportModel UP-TO-DATE
> Task :feature-map:lintReportDebug UP-TO-DATE
> Task :feature-map:lintDebug
> Task :feature-map:lint
> Task :feature-map:generateDebugUnitTestStubRFile UP-TO-DATE
> Task :feature-map:kaptGenerateStubsDebugUnitTestKotlin UP-TO-DATE
> Task :feature-map:kaptDebugUnitTestKotlin SKIPPED
> Task :feature-map:compileDebugUnitTestKotlin NO-SOURCE
> Task :feature-map:javaPreCompileDebugUnitTest UP-TO-DATE
> Task :feature-map:compileDebugUnitTestJavaWithJavac NO-SOURCE
> Task :feature-map:processDebugUnitTestJavaRes NO-SOURCE
> Task :feature-map:testDebugUnitTest NO-SOURCE
> Task :feature-map:preReleaseUnitTestBuild UP-TO-DATE
> Task :feature-map:generateReleaseUnitTestStubRFile UP-TO-DATE
> Task :feature-map:kaptGenerateStubsReleaseUnitTestKotlin UP-TO-DATE
> Task :feature-map:kaptReleaseUnitTestKotlin SKIPPED
> Task :feature-map:compileReleaseUnitTestKotlin NO-SOURCE
> Task :feature-map:javaPreCompileReleaseUnitTest UP-TO-DATE
> Task :feature-map:compileReleaseUnitTestJavaWithJavac NO-SOURCE
> Task :feature-map:processReleaseUnitTestJavaRes NO-SOURCE
> Task :feature-map:testReleaseUnitTest NO-SOURCE
> Task :feature-map:test UP-TO-DATE
> Task :feature-map:check
> Task :feature-map:build
> Task :feature-profile:bundleDebugAar UP-TO-DATE
> Task :feature-profile:assembleDebug UP-TO-DATE
> Task :feature-profile:bundleReleaseAar UP-TO-DATE
> Task :feature-profile:mapReleaseSourceSetPaths UP-TO-DATE
> Task :feature-profile:mergeReleaseResources UP-TO-DATE
> Task :feature-profile:verifyReleaseResources UP-TO-DATE
> Task :feature-profile:assembleRelease UP-TO-DATE
> Task :feature-profile:assemble UP-TO-DATE
> Task :feature-profile:generateDebugLintReportModel UP-TO-DATE
> Task :feature-profile:lintReportDebug UP-TO-DATE
> Task :feature-profile:lintDebug
> Task :feature-profile:lint
> Task :feature-profile:generateDebugUnitTestStubRFile UP-TO-DATE
> Task :feature-profile:kaptGenerateStubsDebugUnitTestKotlin UP-TO-DATE
> Task :feature-profile:kaptDebugUnitTestKotlin SKIPPED
> Task :feature-profile:compileDebugUnitTestKotlin NO-SOURCE
> Task :feature-profile:javaPreCompileDebugUnitTest UP-TO-DATE
> Task :feature-profile:compileDebugUnitTestJavaWithJavac NO-SOURCE
> Task :feature-profile:processDebugUnitTestJavaRes NO-SOURCE
> Task :feature-profile:testDebugUnitTest NO-SOURCE
> Task :feature-profile:preReleaseUnitTestBuild UP-TO-DATE
> Task :feature-profile:generateReleaseUnitTestStubRFile UP-TO-DATE
> Task :feature-profile:kaptGenerateStubsReleaseUnitTestKotlin UP-TO-DATE
> Task :feature-profile:kaptReleaseUnitTestKotlin SKIPPED
> Task :feature-profile:compileReleaseUnitTestKotlin NO-SOURCE
> Task :feature-profile:javaPreCompileReleaseUnitTest UP-TO-DATE
> Task :feature-profile:compileReleaseUnitTestJavaWithJavac NO-SOURCE
> Task :feature-profile:processReleaseUnitTestJavaRes NO-SOURCE
> Task :feature-profile:testReleaseUnitTest NO-SOURCE
> Task :feature-profile:test UP-TO-DATE
> Task :feature-profile:check
> Task :feature-profile:build
> Task :feature-qr:bundleDebugAar UP-TO-DATE
> Task :feature-qr:assembleDebug UP-TO-DATE
> Task :feature-qr:bundleReleaseAar UP-TO-DATE
> Task :feature-qr:mapReleaseSourceSetPaths UP-TO-DATE
> Task :feature-qr:mergeReleaseResources UP-TO-DATE
> Task :feature-qr:verifyReleaseResources UP-TO-DATE
> Task :feature-qr:assembleRelease UP-TO-DATE
> Task :feature-qr:assemble UP-TO-DATE
> Task :feature-qr:generateDebugLintReportModel UP-TO-DATE
> Task :feature-qr:lintReportDebug UP-TO-DATE
> Task :feature-qr:lintDebug
> Task :feature-qr:lint
> Task :feature-qr:generateDebugUnitTestStubRFile UP-TO-DATE
> Task :feature-qr:kaptGenerateStubsDebugUnitTestKotlin UP-TO-DATE
> Task :feature-qr:kaptDebugUnitTestKotlin SKIPPED
> Task :feature-qr:compileDebugUnitTestKotlin NO-SOURCE
> Task :feature-qr:javaPreCompileDebugUnitTest UP-TO-DATE
> Task :feature-qr:compileDebugUnitTestJavaWithJavac NO-SOURCE
> Task :feature-qr:processDebugUnitTestJavaRes NO-SOURCE
> Task :feature-qr:testDebugUnitTest NO-SOURCE
> Task :feature-qr:preReleaseUnitTestBuild UP-TO-DATE
> Task :feature-qr:generateReleaseUnitTestStubRFile UP-TO-DATE
> Task :feature-qr:kaptGenerateStubsReleaseUnitTestKotlin UP-TO-DATE
> Task :feature-qr:kaptReleaseUnitTestKotlin SKIPPED
> Task :feature-qr:compileReleaseUnitTestKotlin NO-SOURCE
> Task :feature-qr:javaPreCompileReleaseUnitTest UP-TO-DATE
> Task :feature-qr:compileReleaseUnitTestJavaWithJavac NO-SOURCE
> Task :feature-qr:processReleaseUnitTestJavaRes NO-SOURCE
> Task :feature-qr:testReleaseUnitTest NO-SOURCE
> Task :feature-qr:test UP-TO-DATE
> Task :feature-qr:check
> Task :feature-qr:build
> Task :feature-quiz:bundleDebugAar UP-TO-DATE
> Task :feature-quiz:assembleDebug UP-TO-DATE
> Task :feature-quiz:bundleReleaseAar UP-TO-DATE
> Task :feature-quiz:mapReleaseSourceSetPaths UP-TO-DATE
> Task :feature-quiz:mergeReleaseResources UP-TO-DATE
> Task :feature-quiz:verifyReleaseResources UP-TO-DATE
> Task :feature-quiz:assembleRelease UP-TO-DATE
> Task :feature-quiz:assemble UP-TO-DATE
> Task :feature-quiz:generateDebugLintReportModel UP-TO-DATE
> Task :feature-quiz:lintReportDebug UP-TO-DATE
> Task :feature-quiz:lintDebug
> Task :feature-quiz:lint
> Task :feature-quiz:generateDebugUnitTestStubRFile UP-TO-DATE
> Task :feature-quiz:kaptGenerateStubsDebugUnitTestKotlin UP-TO-DATE
> Task :feature-quiz:kaptDebugUnitTestKotlin SKIPPED
> Task :feature-quiz:compileDebugUnitTestKotlin NO-SOURCE
> Task :feature-quiz:javaPreCompileDebugUnitTest UP-TO-DATE
> Task :feature-quiz:compileDebugUnitTestJavaWithJavac NO-SOURCE
> Task :feature-quiz:processDebugUnitTestJavaRes NO-SOURCE
> Task :feature-quiz:testDebugUnitTest NO-SOURCE
> Task :feature-quiz:preReleaseUnitTestBuild UP-TO-DATE
> Task :feature-quiz:generateReleaseUnitTestStubRFile UP-TO-DATE
> Task :feature-quiz:kaptGenerateStubsReleaseUnitTestKotlin UP-TO-DATE
> Task :feature-quiz:kaptReleaseUnitTestKotlin SKIPPED
> Task :feature-quiz:compileReleaseUnitTestKotlin NO-SOURCE
> Task :feature-quiz:javaPreCompileReleaseUnitTest UP-TO-DATE
> Task :feature-quiz:compileReleaseUnitTestJavaWithJavac NO-SOURCE
> Task :feature-quiz:processReleaseUnitTestJavaRes NO-SOURCE
> Task :feature-quiz:testReleaseUnitTest NO-SOURCE
> Task :feature-quiz:test UP-TO-DATE
> Task :feature-quiz:check
> Task :feature-quiz:build
> Task :feature-training:bundleDebugAar UP-TO-DATE
> Task :feature-training:assembleDebug UP-TO-DATE
> Task :feature-training:bundleReleaseAar UP-TO-DATE
> Task :feature-training:mapReleaseSourceSetPaths UP-TO-DATE
> Task :feature-training:mergeReleaseResources UP-TO-DATE
> Task :feature-training:verifyReleaseResources UP-TO-DATE
> Task :feature-training:assembleRelease UP-TO-DATE
> Task :feature-training:assemble UP-TO-DATE
> Task :feature-training:generateDebugLintReportModel UP-TO-DATE
> Task :feature-training:lintReportDebug UP-TO-DATE
> Task :feature-training:lintDebug
> Task :feature-training:lint
> Task :feature-training:generateDebugUnitTestStubRFile UP-TO-DATE
> Task :feature-training:kaptGenerateStubsDebugUnitTestKotlin UP-TO-DATE
> Task :feature-training:kaptDebugUnitTestKotlin SKIPPED
> Task :feature-training:compileDebugUnitTestKotlin NO-SOURCE
> Task :feature-training:javaPreCompileDebugUnitTest UP-TO-DATE
> Task :feature-training:compileDebugUnitTestJavaWithJavac NO-SOURCE
> Task :feature-training:processDebugUnitTestJavaRes NO-SOURCE
> Task :feature-training:testDebugUnitTest NO-SOURCE
> Task :feature-training:preReleaseUnitTestBuild UP-TO-DATE
> Task :feature-training:generateReleaseUnitTestStubRFile UP-TO-DATE
> Task :feature-training:kaptGenerateStubsReleaseUnitTestKotlin UP-TO-DATE
> Task :feature-training:kaptReleaseUnitTestKotlin SKIPPED
> Task :feature-training:compileReleaseUnitTestKotlin NO-SOURCE
> Task :feature-training:javaPreCompileReleaseUnitTest UP-TO-DATE
> Task :feature-training:compileReleaseUnitTestJavaWithJavac NO-SOURCE
> Task :feature-training:processReleaseUnitTestJavaRes NO-SOURCE
> Task :feature-training:testReleaseUnitTest NO-SOURCE
> Task :feature-training:test UP-TO-DATE
> Task :feature-training:check
> Task :feature-training:build

Deprecated Gradle features were used in this build, making it incompatible with Gradle 9.0.

You can use '--warning-mode all' to show the individual deprecation warnings and determine if they come from your own scripts or plugins.

For more on this, please refer to https://docs.gradle.org/8.10.2/userguide/command_line_interface.html#sec:command_line_warnings in the Gradle documentation.

BUILD SUCCESSFUL in 8s
983 actionable tasks: 11 executed, 972 up-to-date
  ✓ exit code 0

$ ./gradlew test
> Task :app:checkKotlinGradlePluginConfigurationErrors SKIPPED
> Task :app:preBuild UP-TO-DATE
> Task :app:preDebugBuild UP-TO-DATE
> Task :core-database:preBuild UP-TO-DATE
> Task :core-database:preDebugBuild UP-TO-DATE
> Task :core-database:writeDebugAarMetadata UP-TO-DATE
> Task :core-mapping:preBuild UP-TO-DATE
> Task :core-mapping:preDebugBuild UP-TO-DATE
> Task :core-mapping:writeDebugAarMetadata UP-TO-DATE
> Task :core-navigation:preBuild UP-TO-DATE
> Task :core-navigation:preDebugBuild UP-TO-DATE
> Task :core-navigation:writeDebugAarMetadata UP-TO-DATE
> Task :core-security:preBuild UP-TO-DATE
> Task :core-security:preDebugBuild UP-TO-DATE
> Task :core-security:writeDebugAarMetadata UP-TO-DATE
> Task :core-storage:preBuild UP-TO-DATE
> Task :core-storage:preDebugBuild UP-TO-DATE
> Task :core-storage:writeDebugAarMetadata UP-TO-DATE
> Task :feature-map:preBuild UP-TO-DATE
> Task :feature-map:preDebugBuild UP-TO-DATE
> Task :feature-map:writeDebugAarMetadata UP-TO-DATE
> Task :feature-profile:preBuild UP-TO-DATE
> Task :feature-profile:preDebugBuild UP-TO-DATE
> Task :feature-profile:writeDebugAarMetadata UP-TO-DATE
> Task :feature-qr:preBuild UP-TO-DATE
> Task :feature-qr:preDebugBuild UP-TO-DATE
> Task :feature-qr:writeDebugAarMetadata UP-TO-DATE
> Task :feature-quiz:preBuild UP-TO-DATE
> Task :feature-quiz:preDebugBuild UP-TO-DATE
> Task :feature-quiz:writeDebugAarMetadata UP-TO-DATE
> Task :feature-training:preBuild UP-TO-DATE
> Task :feature-training:preDebugBuild UP-TO-DATE
> Task :feature-training:writeDebugAarMetadata UP-TO-DATE
> Task :app:checkDebugAarMetadata UP-TO-DATE
> Task :app:generateDebugResValues UP-TO-DATE
> Task :core-database:generateDebugResValues UP-TO-DATE
> Task :core-database:generateDebugResources UP-TO-DATE
> Task :core-database:packageDebugResources UP-TO-DATE
> Task :core-mapping:generateDebugResValues UP-TO-DATE
> Task :core-mapping:generateDebugResources UP-TO-DATE
> Task :core-mapping:packageDebugResources UP-TO-DATE
> Task :core-navigation:generateDebugResValues UP-TO-DATE
> Task :core-navigation:generateDebugResources UP-TO-DATE
> Task :core-navigation:packageDebugResources UP-TO-DATE
> Task :core-security:generateDebugResValues UP-TO-DATE
> Task :core-security:generateDebugResources UP-TO-DATE
> Task :core-security:packageDebugResources UP-TO-DATE
> Task :core-storage:generateDebugResValues UP-TO-DATE
> Task :core-storage:generateDebugResources UP-TO-DATE
> Task :core-storage:packageDebugResources UP-TO-DATE
> Task :feature-map:generateDebugResValues UP-TO-DATE
> Task :feature-map:generateDebugResources UP-TO-DATE
> Task :feature-map:packageDebugResources UP-TO-DATE
> Task :feature-profile:generateDebugResValues UP-TO-DATE
> Task :feature-profile:generateDebugResources UP-TO-DATE
> Task :feature-profile:packageDebugResources UP-TO-DATE
> Task :feature-qr:generateDebugResValues UP-TO-DATE
> Task :feature-qr:generateDebugResources UP-TO-DATE
> Task :feature-qr:packageDebugResources UP-TO-DATE
> Task :feature-quiz:generateDebugResValues UP-TO-DATE
> Task :feature-quiz:generateDebugResources UP-TO-DATE
> Task :feature-quiz:packageDebugResources UP-TO-DATE
> Task :feature-training:generateDebugResValues UP-TO-DATE
> Task :feature-training:generateDebugResources UP-TO-DATE
> Task :feature-training:packageDebugResources UP-TO-DATE
> Task :app:mapDebugSourceSetPaths UP-TO-DATE
> Task :app:generateDebugResources UP-TO-DATE
> Task :app:mergeDebugResources UP-TO-DATE
> Task :app:packageDebugResources UP-TO-DATE
> Task :app:parseDebugLocalResources UP-TO-DATE
> Task :app:createDebugCompatibleScreenManifests UP-TO-DATE
> Task :app:extractDeepLinksDebug UP-TO-DATE
> Task :core-database:extractDeepLinksDebug UP-TO-DATE
> Task :core-database:processDebugManifest UP-TO-DATE
> Task :core-mapping:extractDeepLinksDebug UP-TO-DATE
> Task :core-mapping:processDebugManifest UP-TO-DATE
> Task :core-navigation:extractDeepLinksDebug UP-TO-DATE
> Task :core-navigation:processDebugManifest UP-TO-DATE
> Task :core-security:extractDeepLinksDebug UP-TO-DATE
> Task :core-security:processDebugManifest UP-TO-DATE
> Task :core-storage:extractDeepLinksDebug UP-TO-DATE
> Task :core-storage:processDebugManifest UP-TO-DATE
> Task :feature-map:extractDeepLinksDebug UP-TO-DATE
> Task :feature-map:processDebugManifest UP-TO-DATE
> Task :feature-profile:extractDeepLinksDebug UP-TO-DATE
> Task :feature-profile:processDebugManifest UP-TO-DATE
> Task :feature-qr:extractDeepLinksDebug UP-TO-DATE
> Task :feature-qr:processDebugManifest UP-TO-DATE
> Task :feature-quiz:extractDeepLinksDebug UP-TO-DATE
> Task :feature-quiz:processDebugManifest UP-TO-DATE
> Task :feature-training:extractDeepLinksDebug UP-TO-DATE
> Task :feature-training:processDebugManifest UP-TO-DATE
> Task :app:processDebugMainManifest UP-TO-DATE
> Task :app:processDebugManifest UP-TO-DATE
> Task :app:processDebugManifestForPackage UP-TO-DATE
> Task :core-database:compileDebugLibraryResources UP-TO-DATE
> Task :core-database:parseDebugLocalResources UP-TO-DATE
> Task :core-database:generateDebugRFile UP-TO-DATE
> Task :core-mapping:compileDebugLibraryResources UP-TO-DATE
> Task :core-mapping:parseDebugLocalResources UP-TO-DATE
> Task :core-mapping:generateDebugRFile UP-TO-DATE
> Task :core-navigation:compileDebugLibraryResources UP-TO-DATE
> Task :core-navigation:parseDebugLocalResources UP-TO-DATE
> Task :core-navigation:generateDebugRFile UP-TO-DATE
> Task :core-security:compileDebugLibraryResources UP-TO-DATE
> Task :core-security:parseDebugLocalResources UP-TO-DATE
> Task :core-security:generateDebugRFile UP-TO-DATE
> Task :core-storage:compileDebugLibraryResources UP-TO-DATE
> Task :core-storage:parseDebugLocalResources UP-TO-DATE
> Task :core-storage:generateDebugRFile UP-TO-DATE
> Task :feature-map:compileDebugLibraryResources UP-TO-DATE
> Task :feature-map:parseDebugLocalResources UP-TO-DATE
> Task :feature-map:generateDebugRFile UP-TO-DATE
> Task :feature-profile:compileDebugLibraryResources UP-TO-DATE
> Task :feature-profile:parseDebugLocalResources UP-TO-DATE
> Task :feature-profile:generateDebugRFile UP-TO-DATE
> Task :feature-qr:compileDebugLibraryResources UP-TO-DATE
> Task :feature-qr:parseDebugLocalResources UP-TO-DATE
> Task :feature-qr:generateDebugRFile UP-TO-DATE
> Task :feature-quiz:compileDebugLibraryResources UP-TO-DATE
> Task :feature-quiz:parseDebugLocalResources UP-TO-DATE
> Task :feature-quiz:generateDebugRFile UP-TO-DATE
> Task :feature-training:compileDebugLibraryResources UP-TO-DATE
> Task :feature-training:parseDebugLocalResources UP-TO-DATE
> Task :feature-training:generateDebugRFile UP-TO-DATE
> Task :app:processDebugResources UP-TO-DATE
> Task :core-database:checkKotlinGradlePluginConfigurationErrors SKIPPED
> Task :core-database:kspDebugKotlin UP-TO-DATE
> Task :core-database:kaptGenerateStubsDebugKotlin UP-TO-DATE
> Task :core-database:kaptDebugKotlin SKIPPED
> Task :core-database:compileDebugKotlin UP-TO-DATE
> Task :core-database:javaPreCompileDebug UP-TO-DATE
> Task :core-database:compileDebugJavaWithJavac NO-SOURCE
> Task :core-database:bundleLibCompileToJarDebug UP-TO-DATE
> Task :core-mapping:checkKotlinGradlePluginConfigurationErrors SKIPPED
> Task :core-mapping:kaptGenerateStubsDebugKotlin UP-TO-DATE
> Task :core-mapping:kaptDebugKotlin SKIPPED
> Task :core-mapping:compileDebugKotlin NO-SOURCE
> Task :core-mapping:javaPreCompileDebug UP-TO-DATE
> Task :core-mapping:compileDebugJavaWithJavac NO-SOURCE
> Task :core-mapping:bundleLibCompileToJarDebug UP-TO-DATE
> Task :core-navigation:checkKotlinGradlePluginConfigurationErrors SKIPPED
> Task :core-navigation:kaptGenerateStubsDebugKotlin UP-TO-DATE
> Task :core-navigation:kaptDebugKotlin SKIPPED
> Task :core-navigation:compileDebugKotlin UP-TO-DATE
> Task :core-navigation:javaPreCompileDebug UP-TO-DATE
> Task :core-navigation:compileDebugJavaWithJavac NO-SOURCE
> Task :core-navigation:bundleLibCompileToJarDebug UP-TO-DATE
> Task :core-security:checkKotlinGradlePluginConfigurationErrors SKIPPED
> Task :core-security:kaptGenerateStubsDebugKotlin UP-TO-DATE
> Task :core-security:kaptDebugKotlin SKIPPED
> Task :core-security:compileDebugKotlin UP-TO-DATE
> Task :core-security:javaPreCompileDebug UP-TO-DATE
> Task :core-security:compileDebugJavaWithJavac NO-SOURCE
> Task :core-security:bundleLibCompileToJarDebug UP-TO-DATE
> Task :core-storage:checkKotlinGradlePluginConfigurationErrors SKIPPED
> Task :core-storage:kaptGenerateStubsDebugKotlin UP-TO-DATE
> Task :core-storage:kaptDebugKotlin SKIPPED
> Task :core-storage:compileDebugKotlin UP-TO-DATE
> Task :core-storage:javaPreCompileDebug UP-TO-DATE
> Task :core-storage:compileDebugJavaWithJavac NO-SOURCE
> Task :core-storage:bundleLibCompileToJarDebug UP-TO-DATE
> Task :feature-map:checkKotlinGradlePluginConfigurationErrors SKIPPED
> Task :feature-map:kaptGenerateStubsDebugKotlin UP-TO-DATE
> Task :feature-map:kaptDebugKotlin SKIPPED
> Task :feature-map:compileDebugKotlin UP-TO-DATE
> Task :feature-map:javaPreCompileDebug UP-TO-DATE
> Task :feature-map:compileDebugJavaWithJavac NO-SOURCE
> Task :feature-map:bundleLibCompileToJarDebug UP-TO-DATE
> Task :feature-profile:checkKotlinGradlePluginConfigurationErrors SKIPPED
> Task :feature-profile:kaptGenerateStubsDebugKotlin UP-TO-DATE
> Task :feature-profile:kaptDebugKotlin SKIPPED
> Task :feature-profile:compileDebugKotlin UP-TO-DATE
> Task :feature-profile:javaPreCompileDebug UP-TO-DATE
> Task :feature-profile:compileDebugJavaWithJavac NO-SOURCE
> Task :feature-profile:bundleLibCompileToJarDebug UP-TO-DATE
> Task :feature-qr:checkKotlinGradlePluginConfigurationErrors SKIPPED
> Task :feature-qr:kaptGenerateStubsDebugKotlin UP-TO-DATE
> Task :feature-qr:kaptDebugKotlin SKIPPED
> Task :feature-qr:compileDebugKotlin UP-TO-DATE
> Task :feature-qr:javaPreCompileDebug UP-TO-DATE
> Task :feature-qr:compileDebugJavaWithJavac NO-SOURCE
> Task :feature-qr:bundleLibCompileToJarDebug UP-TO-DATE
> Task :feature-quiz:checkKotlinGradlePluginConfigurationErrors SKIPPED
> Task :feature-quiz:kaptGenerateStubsDebugKotlin UP-TO-DATE
> Task :feature-quiz:kaptDebugKotlin SKIPPED
> Task :feature-quiz:compileDebugKotlin UP-TO-DATE
> Task :feature-quiz:javaPreCompileDebug UP-TO-DATE
> Task :feature-quiz:compileDebugJavaWithJavac NO-SOURCE
> Task :feature-quiz:bundleLibCompileToJarDebug UP-TO-DATE
> Task :feature-training:checkKotlinGradlePluginConfigurationErrors SKIPPED
> Task :feature-training:kaptGenerateStubsDebugKotlin UP-TO-DATE
> Task :feature-training:kaptDebugKotlin SKIPPED
> Task :feature-training:compileDebugKotlin UP-TO-DATE
> Task :feature-training:javaPreCompileDebug UP-TO-DATE
> Task :feature-training:compileDebugJavaWithJavac NO-SOURCE
> Task :feature-training:bundleLibCompileToJarDebug UP-TO-DATE
> Task :app:kaptGenerateStubsDebugKotlin UP-TO-DATE
> Task :app:kaptDebugKotlin SKIPPED
> Task :app:compileDebugKotlin UP-TO-DATE
> Task :app:javaPreCompileDebug UP-TO-DATE
> Task :app:compileDebugJavaWithJavac NO-SOURCE
> Task :app:bundleDebugClassesToRuntimeJar UP-TO-DATE
> Task :app:bundleDebugClassesToCompileJar UP-TO-DATE
> Task :app:kaptGenerateStubsDebugUnitTestKotlin UP-TO-DATE
> Task :app:kaptDebugUnitTestKotlin SKIPPED
> Task :app:compileDebugUnitTestKotlin NO-SOURCE
> Task :app:preDebugUnitTestBuild UP-TO-DATE
> Task :app:javaPreCompileDebugUnitTest UP-TO-DATE
> Task :app:compileDebugUnitTestJavaWithJavac NO-SOURCE
> Task :app:generateDebugJacocoPropertiesFile UP-TO-DATE
> Task :app:processDebugJavaRes UP-TO-DATE
> Task :app:processDebugUnitTestJavaRes NO-SOURCE
> Task :core-database:bundleLibRuntimeToJarDebug UP-TO-DATE
> Task :core-database:processDebugJavaRes UP-TO-DATE
> Task :core-mapping:bundleLibRuntimeToJarDebug UP-TO-DATE
> Task :core-mapping:processDebugJavaRes NO-SOURCE
> Task :core-navigation:bundleLibRuntimeToJarDebug UP-TO-DATE
> Task :core-navigation:processDebugJavaRes UP-TO-DATE
> Task :core-security:bundleLibRuntimeToJarDebug UP-TO-DATE
> Task :core-security:processDebugJavaRes UP-TO-DATE
> Task :core-storage:bundleLibRuntimeToJarDebug UP-TO-DATE
> Task :core-storage:processDebugJavaRes UP-TO-DATE
> Task :feature-map:bundleLibRuntimeToJarDebug UP-TO-DATE
> Task :feature-map:processDebugJavaRes UP-TO-DATE
> Task :feature-profile:bundleLibRuntimeToJarDebug UP-TO-DATE
> Task :feature-profile:processDebugJavaRes UP-TO-DATE
> Task :feature-qr:bundleLibRuntimeToJarDebug UP-TO-DATE
> Task :feature-qr:processDebugJavaRes UP-TO-DATE
> Task :feature-quiz:bundleLibRuntimeToJarDebug UP-TO-DATE
> Task :feature-quiz:processDebugJavaRes UP-TO-DATE
> Task :feature-training:bundleLibRuntimeToJarDebug UP-TO-DATE
> Task :feature-training:processDebugJavaRes UP-TO-DATE
> Task :app:testDebugUnitTest NO-SOURCE
> Task :app:buildKotlinToolingMetadata UP-TO-DATE
> Task :app:preReleaseBuild UP-TO-DATE
> Task :core-database:preReleaseBuild UP-TO-DATE
> Task :core-database:writeReleaseAarMetadata UP-TO-DATE
> Task :core-mapping:preReleaseBuild UP-TO-DATE
> Task :core-mapping:writeReleaseAarMetadata UP-TO-DATE
> Task :core-navigation:preReleaseBuild UP-TO-DATE
> Task :core-navigation:writeReleaseAarMetadata UP-TO-DATE
> Task :core-security:preReleaseBuild UP-TO-DATE
> Task :core-security:writeReleaseAarMetadata UP-TO-DATE
> Task :core-storage:preReleaseBuild UP-TO-DATE
> Task :core-storage:writeReleaseAarMetadata UP-TO-DATE
> Task :feature-map:preReleaseBuild UP-TO-DATE
> Task :feature-map:writeReleaseAarMetadata UP-TO-DATE
> Task :feature-profile:preReleaseBuild UP-TO-DATE
> Task :feature-profile:writeReleaseAarMetadata UP-TO-DATE
> Task :feature-qr:preReleaseBuild UP-TO-DATE
> Task :feature-qr:writeReleaseAarMetadata UP-TO-DATE
> Task :feature-quiz:preReleaseBuild UP-TO-DATE
> Task :feature-quiz:writeReleaseAarMetadata UP-TO-DATE
> Task :feature-training:preReleaseBuild UP-TO-DATE
> Task :feature-training:writeReleaseAarMetadata UP-TO-DATE
> Task :app:checkReleaseAarMetadata UP-TO-DATE
> Task :app:generateReleaseResValues UP-TO-DATE
> Task :core-database:generateReleaseResValues UP-TO-DATE
> Task :core-database:generateReleaseResources UP-TO-DATE
> Task :core-database:packageReleaseResources UP-TO-DATE
> Task :core-mapping:generateReleaseResValues UP-TO-DATE
> Task :core-mapping:generateReleaseResources UP-TO-DATE
> Task :core-mapping:packageReleaseResources UP-TO-DATE
> Task :core-navigation:generateReleaseResValues UP-TO-DATE
> Task :core-navigation:generateReleaseResources UP-TO-DATE
> Task :core-navigation:packageReleaseResources UP-TO-DATE
> Task :core-security:generateReleaseResValues UP-TO-DATE
> Task :core-security:generateReleaseResources UP-TO-DATE
> Task :core-security:packageReleaseResources UP-TO-DATE
> Task :core-storage:generateReleaseResValues UP-TO-DATE
> Task :core-storage:generateReleaseResources UP-TO-DATE
> Task :core-storage:packageReleaseResources UP-TO-DATE
> Task :feature-map:generateReleaseResValues UP-TO-DATE
> Task :feature-map:generateReleaseResources UP-TO-DATE
> Task :feature-map:packageReleaseResources UP-TO-DATE
> Task :feature-profile:generateReleaseResValues UP-TO-DATE
> Task :feature-profile:generateReleaseResources UP-TO-DATE
> Task :feature-profile:packageReleaseResources UP-TO-DATE
> Task :feature-qr:generateReleaseResValues UP-TO-DATE
> Task :feature-qr:generateReleaseResources UP-TO-DATE
> Task :feature-qr:packageReleaseResources UP-TO-DATE
> Task :feature-quiz:generateReleaseResValues UP-TO-DATE
> Task :feature-quiz:generateReleaseResources UP-TO-DATE
> Task :feature-quiz:packageReleaseResources UP-TO-DATE
> Task :feature-training:generateReleaseResValues UP-TO-DATE
> Task :feature-training:generateReleaseResources UP-TO-DATE
> Task :feature-training:packageReleaseResources UP-TO-DATE
> Task :app:mapReleaseSourceSetPaths UP-TO-DATE
> Task :app:generateReleaseResources UP-TO-DATE
> Task :app:mergeReleaseResources UP-TO-DATE
> Task :app:packageReleaseResources UP-TO-DATE
> Task :app:parseReleaseLocalResources UP-TO-DATE
> Task :app:createReleaseCompatibleScreenManifests UP-TO-DATE
> Task :app:extractDeepLinksRelease UP-TO-DATE
> Task :core-database:extractDeepLinksRelease UP-TO-DATE
> Task :core-database:processReleaseManifest UP-TO-DATE
> Task :core-mapping:extractDeepLinksRelease UP-TO-DATE
> Task :core-mapping:processReleaseManifest UP-TO-DATE
> Task :core-navigation:extractDeepLinksRelease UP-TO-DATE
> Task :core-navigation:processReleaseManifest UP-TO-DATE
> Task :core-security:extractDeepLinksRelease UP-TO-DATE
> Task :core-security:processReleaseManifest UP-TO-DATE
> Task :core-storage:extractDeepLinksRelease UP-TO-DATE
> Task :core-storage:processReleaseManifest UP-TO-DATE
> Task :feature-map:extractDeepLinksRelease UP-TO-DATE
> Task :feature-map:processReleaseManifest UP-TO-DATE
> Task :feature-profile:extractDeepLinksRelease UP-TO-DATE
> Task :feature-profile:processReleaseManifest UP-TO-DATE
> Task :feature-qr:extractDeepLinksRelease UP-TO-DATE
> Task :feature-qr:processReleaseManifest UP-TO-DATE
> Task :feature-quiz:extractDeepLinksRelease UP-TO-DATE
> Task :feature-quiz:processReleaseManifest UP-TO-DATE
> Task :feature-training:extractDeepLinksRelease UP-TO-DATE
> Task :feature-training:processReleaseManifest UP-TO-DATE
> Task :app:processReleaseMainManifest UP-TO-DATE
> Task :app:processReleaseManifest UP-TO-DATE
> Task :app:processReleaseManifestForPackage UP-TO-DATE
> Task :core-database:compileReleaseLibraryResources UP-TO-DATE
> Task :core-database:parseReleaseLocalResources UP-TO-DATE
> Task :core-database:generateReleaseRFile UP-TO-DATE
> Task :core-mapping:compileReleaseLibraryResources UP-TO-DATE
> Task :core-mapping:parseReleaseLocalResources UP-TO-DATE
> Task :core-mapping:generateReleaseRFile UP-TO-DATE
> Task :core-navigation:compileReleaseLibraryResources UP-TO-DATE
> Task :core-navigation:parseReleaseLocalResources UP-TO-DATE
> Task :core-navigation:generateReleaseRFile UP-TO-DATE
> Task :core-security:compileReleaseLibraryResources UP-TO-DATE
> Task :core-security:parseReleaseLocalResources UP-TO-DATE
> Task :core-security:generateReleaseRFile UP-TO-DATE
> Task :core-storage:compileReleaseLibraryResources UP-TO-DATE
> Task :core-storage:parseReleaseLocalResources UP-TO-DATE
> Task :core-storage:generateReleaseRFile UP-TO-DATE
> Task :feature-map:compileReleaseLibraryResources UP-TO-DATE
> Task :feature-map:parseReleaseLocalResources UP-TO-DATE
> Task :feature-map:generateReleaseRFile UP-TO-DATE
> Task :feature-profile:compileReleaseLibraryResources UP-TO-DATE
> Task :feature-profile:parseReleaseLocalResources UP-TO-DATE
> Task :feature-profile:generateReleaseRFile UP-TO-DATE
> Task :feature-qr:compileReleaseLibraryResources UP-TO-DATE
> Task :feature-qr:parseReleaseLocalResources UP-TO-DATE
> Task :feature-qr:generateReleaseRFile UP-TO-DATE
> Task :feature-quiz:compileReleaseLibraryResources UP-TO-DATE
> Task :feature-quiz:parseReleaseLocalResources UP-TO-DATE
> Task :feature-quiz:generateReleaseRFile UP-TO-DATE
> Task :feature-training:compileReleaseLibraryResources UP-TO-DATE
> Task :feature-training:parseReleaseLocalResources UP-TO-DATE
> Task :feature-training:generateReleaseRFile UP-TO-DATE
> Task :app:processReleaseResources UP-TO-DATE
> Task :core-database:kspReleaseKotlin UP-TO-DATE
> Task :core-database:kaptGenerateStubsReleaseKotlin UP-TO-DATE
> Task :core-database:kaptReleaseKotlin SKIPPED
> Task :core-database:compileReleaseKotlin UP-TO-DATE
> Task :core-database:javaPreCompileRelease UP-TO-DATE
> Task :core-database:compileReleaseJavaWithJavac NO-SOURCE
> Task :core-database:bundleLibCompileToJarRelease UP-TO-DATE
> Task :core-mapping:kaptGenerateStubsReleaseKotlin UP-TO-DATE
> Task :core-mapping:kaptReleaseKotlin SKIPPED
> Task :core-mapping:compileReleaseKotlin NO-SOURCE
> Task :core-mapping:javaPreCompileRelease UP-TO-DATE
> Task :core-mapping:compileReleaseJavaWithJavac NO-SOURCE
> Task :core-mapping:bundleLibCompileToJarRelease UP-TO-DATE
> Task :core-navigation:kaptGenerateStubsReleaseKotlin UP-TO-DATE
> Task :core-navigation:kaptReleaseKotlin SKIPPED
> Task :core-navigation:compileReleaseKotlin UP-TO-DATE
> Task :core-navigation:javaPreCompileRelease UP-TO-DATE
> Task :core-navigation:compileReleaseJavaWithJavac NO-SOURCE
> Task :core-navigation:bundleLibCompileToJarRelease UP-TO-DATE
> Task :core-security:kaptGenerateStubsReleaseKotlin UP-TO-DATE
> Task :core-security:kaptReleaseKotlin SKIPPED
> Task :core-security:compileReleaseKotlin UP-TO-DATE
> Task :core-security:javaPreCompileRelease UP-TO-DATE
> Task :core-security:compileReleaseJavaWithJavac NO-SOURCE
> Task :core-security:bundleLibCompileToJarRelease UP-TO-DATE
> Task :core-storage:kaptGenerateStubsReleaseKotlin UP-TO-DATE
> Task :core-storage:kaptReleaseKotlin SKIPPED
> Task :core-storage:compileReleaseKotlin UP-TO-DATE
> Task :core-storage:javaPreCompileRelease UP-TO-DATE
> Task :core-storage:compileReleaseJavaWithJavac NO-SOURCE
> Task :core-storage:bundleLibCompileToJarRelease UP-TO-DATE
> Task :feature-map:kaptGenerateStubsReleaseKotlin UP-TO-DATE
> Task :feature-map:kaptReleaseKotlin SKIPPED
> Task :feature-map:compileReleaseKotlin UP-TO-DATE
> Task :feature-map:javaPreCompileRelease UP-TO-DATE
> Task :feature-map:compileReleaseJavaWithJavac NO-SOURCE
> Task :feature-map:bundleLibCompileToJarRelease UP-TO-DATE
> Task :feature-profile:kaptGenerateStubsReleaseKotlin UP-TO-DATE
> Task :feature-profile:kaptReleaseKotlin SKIPPED
> Task :feature-profile:compileReleaseKotlin UP-TO-DATE
> Task :feature-profile:javaPreCompileRelease UP-TO-DATE
> Task :feature-profile:compileReleaseJavaWithJavac NO-SOURCE
> Task :feature-profile:bundleLibCompileToJarRelease UP-TO-DATE
> Task :feature-qr:kaptGenerateStubsReleaseKotlin UP-TO-DATE
> Task :feature-qr:kaptReleaseKotlin SKIPPED
> Task :feature-qr:compileReleaseKotlin UP-TO-DATE
> Task :feature-qr:javaPreCompileRelease UP-TO-DATE
> Task :feature-qr:compileReleaseJavaWithJavac NO-SOURCE
> Task :feature-qr:bundleLibCompileToJarRelease UP-TO-DATE
> Task :feature-quiz:kaptGenerateStubsReleaseKotlin UP-TO-DATE
> Task :feature-quiz:kaptReleaseKotlin SKIPPED
> Task :feature-quiz:compileReleaseKotlin UP-TO-DATE
> Task :feature-quiz:javaPreCompileRelease UP-TO-DATE
> Task :feature-quiz:compileReleaseJavaWithJavac NO-SOURCE
> Task :feature-quiz:bundleLibCompileToJarRelease UP-TO-DATE
> Task :feature-training:kaptGenerateStubsReleaseKotlin UP-TO-DATE
> Task :feature-training:kaptReleaseKotlin SKIPPED
> Task :feature-training:compileReleaseKotlin UP-TO-DATE
> Task :feature-training:javaPreCompileRelease UP-TO-DATE
> Task :feature-training:compileReleaseJavaWithJavac NO-SOURCE
> Task :feature-training:bundleLibCompileToJarRelease UP-TO-DATE
> Task :app:kaptGenerateStubsReleaseKotlin UP-TO-DATE
> Task :app:kaptReleaseKotlin SKIPPED
> Task :app:compileReleaseKotlin UP-TO-DATE
> Task :app:javaPreCompileRelease UP-TO-DATE
> Task :app:compileReleaseJavaWithJavac NO-SOURCE
> Task :app:bundleReleaseClassesToRuntimeJar UP-TO-DATE
> Task :app:bundleReleaseClassesToCompileJar UP-TO-DATE
> Task :app:kaptGenerateStubsReleaseUnitTestKotlin UP-TO-DATE
> Task :app:kaptReleaseUnitTestKotlin SKIPPED
> Task :app:compileReleaseUnitTestKotlin NO-SOURCE
> Task :app:preReleaseUnitTestBuild UP-TO-DATE
> Task :app:javaPreCompileReleaseUnitTest UP-TO-DATE
> Task :app:compileReleaseUnitTestJavaWithJavac NO-SOURCE
> Task :app:processReleaseJavaRes UP-TO-DATE
> Task :app:processReleaseUnitTestJavaRes NO-SOURCE
> Task :core-database:bundleLibRuntimeToJarRelease UP-TO-DATE
> Task :core-database:processReleaseJavaRes UP-TO-DATE
> Task :core-mapping:bundleLibRuntimeToJarRelease UP-TO-DATE
> Task :core-mapping:processReleaseJavaRes NO-SOURCE
> Task :core-navigation:bundleLibRuntimeToJarRelease UP-TO-DATE
> Task :core-navigation:processReleaseJavaRes UP-TO-DATE
> Task :core-security:bundleLibRuntimeToJarRelease UP-TO-DATE
> Task :core-security:processReleaseJavaRes UP-TO-DATE
> Task :core-storage:bundleLibRuntimeToJarRelease UP-TO-DATE
> Task :core-storage:processReleaseJavaRes UP-TO-DATE
> Task :feature-map:bundleLibRuntimeToJarRelease UP-TO-DATE
> Task :feature-map:processReleaseJavaRes UP-TO-DATE
> Task :feature-profile:bundleLibRuntimeToJarRelease UP-TO-DATE
> Task :feature-profile:processReleaseJavaRes UP-TO-DATE
> Task :feature-qr:bundleLibRuntimeToJarRelease UP-TO-DATE
> Task :feature-qr:processReleaseJavaRes UP-TO-DATE
> Task :feature-quiz:bundleLibRuntimeToJarRelease UP-TO-DATE
> Task :feature-quiz:processReleaseJavaRes UP-TO-DATE
> Task :feature-training:bundleLibRuntimeToJarRelease UP-TO-DATE
> Task :feature-training:processReleaseJavaRes UP-TO-DATE
> Task :app:testReleaseUnitTest NO-SOURCE
> Task :app:test UP-TO-DATE
> Task :core-database:preDebugUnitTestBuild UP-TO-DATE
> Task :core-database:generateDebugUnitTestStubRFile UP-TO-DATE
> Task :core-database:kspDebugUnitTestKotlin NO-SOURCE
> Task :core-database:kaptGenerateStubsDebugUnitTestKotlin UP-TO-DATE
> Task :core-database:kaptDebugUnitTestKotlin SKIPPED
> Task :core-database:compileDebugUnitTestKotlin NO-SOURCE
> Task :core-database:javaPreCompileDebugUnitTest UP-TO-DATE
> Task :core-database:compileDebugUnitTestJavaWithJavac NO-SOURCE
> Task :core-database:processDebugUnitTestJavaRes NO-SOURCE
> Task :core-database:testDebugUnitTest NO-SOURCE
> Task :core-database:preReleaseUnitTestBuild UP-TO-DATE
> Task :core-database:generateReleaseUnitTestStubRFile UP-TO-DATE
> Task :core-database:kspReleaseUnitTestKotlin NO-SOURCE
> Task :core-database:kaptGenerateStubsReleaseUnitTestKotlin UP-TO-DATE
> Task :core-database:kaptReleaseUnitTestKotlin SKIPPED
> Task :core-database:compileReleaseUnitTestKotlin NO-SOURCE
> Task :core-database:javaPreCompileReleaseUnitTest UP-TO-DATE
> Task :core-database:compileReleaseUnitTestJavaWithJavac NO-SOURCE
> Task :core-database:processReleaseUnitTestJavaRes NO-SOURCE
> Task :core-database:testReleaseUnitTest NO-SOURCE
> Task :core-database:test UP-TO-DATE
> Task :core-mapping:preDebugUnitTestBuild UP-TO-DATE
> Task :core-mapping:generateDebugUnitTestStubRFile UP-TO-DATE
> Task :core-mapping:kaptGenerateStubsDebugUnitTestKotlin UP-TO-DATE
> Task :core-mapping:kaptDebugUnitTestKotlin SKIPPED
> Task :core-mapping:compileDebugUnitTestKotlin NO-SOURCE
> Task :core-mapping:javaPreCompileDebugUnitTest UP-TO-DATE
> Task :core-mapping:compileDebugUnitTestJavaWithJavac NO-SOURCE
> Task :core-mapping:processDebugUnitTestJavaRes NO-SOURCE
> Task :core-mapping:testDebugUnitTest NO-SOURCE
> Task :core-mapping:preReleaseUnitTestBuild UP-TO-DATE
> Task :core-mapping:generateReleaseUnitTestStubRFile UP-TO-DATE
> Task :core-mapping:kaptGenerateStubsReleaseUnitTestKotlin UP-TO-DATE
> Task :core-mapping:kaptReleaseUnitTestKotlin SKIPPED
> Task :core-mapping:compileReleaseUnitTestKotlin NO-SOURCE
> Task :core-mapping:javaPreCompileReleaseUnitTest UP-TO-DATE
> Task :core-mapping:compileReleaseUnitTestJavaWithJavac NO-SOURCE
> Task :core-mapping:processReleaseUnitTestJavaRes NO-SOURCE
> Task :core-mapping:testReleaseUnitTest NO-SOURCE
> Task :core-mapping:test UP-TO-DATE
> Task :core-navigation:preDebugUnitTestBuild UP-TO-DATE
> Task :core-navigation:generateDebugUnitTestStubRFile UP-TO-DATE
> Task :core-navigation:kaptGenerateStubsDebugUnitTestKotlin UP-TO-DATE
> Task :core-navigation:kaptDebugUnitTestKotlin SKIPPED
> Task :core-navigation:compileDebugUnitTestKotlin NO-SOURCE
> Task :core-navigation:javaPreCompileDebugUnitTest UP-TO-DATE
> Task :core-navigation:compileDebugUnitTestJavaWithJavac NO-SOURCE
> Task :core-navigation:processDebugUnitTestJavaRes NO-SOURCE
> Task :core-navigation:testDebugUnitTest NO-SOURCE
> Task :core-navigation:preReleaseUnitTestBuild UP-TO-DATE
> Task :core-navigation:generateReleaseUnitTestStubRFile UP-TO-DATE
> Task :core-navigation:kaptGenerateStubsReleaseUnitTestKotlin UP-TO-DATE
> Task :core-navigation:kaptReleaseUnitTestKotlin SKIPPED
> Task :core-navigation:compileReleaseUnitTestKotlin NO-SOURCE
> Task :core-navigation:javaPreCompileReleaseUnitTest UP-TO-DATE
> Task :core-navigation:compileReleaseUnitTestJavaWithJavac NO-SOURCE
> Task :core-navigation:processReleaseUnitTestJavaRes NO-SOURCE
> Task :core-navigation:testReleaseUnitTest NO-SOURCE
> Task :core-navigation:test UP-TO-DATE
> Task :core-security:preDebugUnitTestBuild UP-TO-DATE
> Task :core-security:generateDebugUnitTestStubRFile UP-TO-DATE
> Task :core-security:kaptGenerateStubsDebugUnitTestKotlin UP-TO-DATE
> Task :core-security:kaptDebugUnitTestKotlin SKIPPED
> Task :core-security:compileDebugUnitTestKotlin NO-SOURCE
> Task :core-security:javaPreCompileDebugUnitTest UP-TO-DATE
> Task :core-security:compileDebugUnitTestJavaWithJavac NO-SOURCE
> Task :core-security:processDebugUnitTestJavaRes NO-SOURCE
> Task :core-security:testDebugUnitTest NO-SOURCE
> Task :core-security:preReleaseUnitTestBuild UP-TO-DATE
> Task :core-security:generateReleaseUnitTestStubRFile UP-TO-DATE
> Task :core-security:kaptGenerateStubsReleaseUnitTestKotlin UP-TO-DATE
> Task :core-security:kaptReleaseUnitTestKotlin SKIPPED
> Task :core-security:compileReleaseUnitTestKotlin NO-SOURCE
> Task :core-security:javaPreCompileReleaseUnitTest UP-TO-DATE
> Task :core-security:compileReleaseUnitTestJavaWithJavac NO-SOURCE
> Task :core-security:processReleaseUnitTestJavaRes NO-SOURCE
> Task :core-security:testReleaseUnitTest NO-SOURCE
> Task :core-security:test UP-TO-DATE
> Task :core-storage:preDebugUnitTestBuild UP-TO-DATE
> Task :core-storage:generateDebugUnitTestStubRFile UP-TO-DATE
> Task :core-storage:kaptGenerateStubsDebugUnitTestKotlin UP-TO-DATE
> Task :core-storage:kaptDebugUnitTestKotlin SKIPPED
> Task :core-storage:compileDebugUnitTestKotlin NO-SOURCE
> Task :core-storage:javaPreCompileDebugUnitTest UP-TO-DATE
> Task :core-storage:compileDebugUnitTestJavaWithJavac NO-SOURCE
> Task :core-storage:processDebugUnitTestJavaRes NO-SOURCE
> Task :core-storage:testDebugUnitTest NO-SOURCE
> Task :core-storage:preReleaseUnitTestBuild UP-TO-DATE
> Task :core-storage:generateReleaseUnitTestStubRFile UP-TO-DATE
> Task :core-storage:kaptGenerateStubsReleaseUnitTestKotlin UP-TO-DATE
> Task :core-storage:kaptReleaseUnitTestKotlin SKIPPED
> Task :core-storage:compileReleaseUnitTestKotlin NO-SOURCE
> Task :core-storage:javaPreCompileReleaseUnitTest UP-TO-DATE
> Task :core-storage:compileReleaseUnitTestJavaWithJavac NO-SOURCE
> Task :core-storage:processReleaseUnitTestJavaRes NO-SOURCE
> Task :core-storage:testReleaseUnitTest NO-SOURCE
> Task :core-storage:test UP-TO-DATE
> Task :feature-map:preDebugUnitTestBuild UP-TO-DATE
> Task :feature-map:generateDebugUnitTestStubRFile UP-TO-DATE
> Task :feature-map:kaptGenerateStubsDebugUnitTestKotlin UP-TO-DATE
> Task :feature-map:kaptDebugUnitTestKotlin SKIPPED
> Task :feature-map:compileDebugUnitTestKotlin NO-SOURCE
> Task :feature-map:javaPreCompileDebugUnitTest UP-TO-DATE
> Task :feature-map:compileDebugUnitTestJavaWithJavac NO-SOURCE
> Task :feature-map:processDebugUnitTestJavaRes NO-SOURCE
> Task :feature-map:testDebugUnitTest NO-SOURCE
> Task :feature-map:preReleaseUnitTestBuild UP-TO-DATE
> Task :feature-map:generateReleaseUnitTestStubRFile UP-TO-DATE
> Task :feature-map:kaptGenerateStubsReleaseUnitTestKotlin UP-TO-DATE
> Task :feature-map:kaptReleaseUnitTestKotlin SKIPPED
> Task :feature-map:compileReleaseUnitTestKotlin NO-SOURCE
> Task :feature-map:javaPreCompileReleaseUnitTest UP-TO-DATE
> Task :feature-map:compileReleaseUnitTestJavaWithJavac NO-SOURCE
> Task :feature-map:processReleaseUnitTestJavaRes NO-SOURCE
> Task :feature-map:testReleaseUnitTest NO-SOURCE
> Task :feature-map:test UP-TO-DATE
> Task :feature-profile:preDebugUnitTestBuild UP-TO-DATE
> Task :feature-profile:generateDebugUnitTestStubRFile UP-TO-DATE
> Task :feature-profile:kaptGenerateStubsDebugUnitTestKotlin UP-TO-DATE
> Task :feature-profile:kaptDebugUnitTestKotlin SKIPPED
> Task :feature-profile:compileDebugUnitTestKotlin NO-SOURCE
> Task :feature-profile:javaPreCompileDebugUnitTest UP-TO-DATE
> Task :feature-profile:compileDebugUnitTestJavaWithJavac NO-SOURCE
> Task :feature-profile:processDebugUnitTestJavaRes NO-SOURCE
> Task :feature-profile:testDebugUnitTest NO-SOURCE
> Task :feature-profile:preReleaseUnitTestBuild UP-TO-DATE
> Task :feature-profile:generateReleaseUnitTestStubRFile UP-TO-DATE
> Task :feature-profile:kaptGenerateStubsReleaseUnitTestKotlin UP-TO-DATE
> Task :feature-profile:kaptReleaseUnitTestKotlin SKIPPED
> Task :feature-profile:compileReleaseUnitTestKotlin NO-SOURCE
> Task :feature-profile:javaPreCompileReleaseUnitTest UP-TO-DATE
> Task :feature-profile:compileReleaseUnitTestJavaWithJavac NO-SOURCE
> Task :feature-profile:processReleaseUnitTestJavaRes NO-SOURCE
> Task :feature-profile:testReleaseUnitTest NO-SOURCE
> Task :feature-profile:test UP-TO-DATE
> Task :feature-qr:preDebugUnitTestBuild UP-TO-DATE
> Task :feature-qr:generateDebugUnitTestStubRFile UP-TO-DATE
> Task :feature-qr:kaptGenerateStubsDebugUnitTestKotlin UP-TO-DATE
> Task :feature-qr:kaptDebugUnitTestKotlin SKIPPED
> Task :feature-qr:compileDebugUnitTestKotlin NO-SOURCE
> Task :feature-qr:javaPreCompileDebugUnitTest UP-TO-DATE
> Task :feature-qr:compileDebugUnitTestJavaWithJavac NO-SOURCE
> Task :feature-qr:processDebugUnitTestJavaRes NO-SOURCE
> Task :feature-qr:testDebugUnitTest NO-SOURCE
> Task :feature-qr:preReleaseUnitTestBuild UP-TO-DATE
> Task :feature-qr:generateReleaseUnitTestStubRFile UP-TO-DATE
> Task :feature-qr:kaptGenerateStubsReleaseUnitTestKotlin UP-TO-DATE
> Task :feature-qr:kaptReleaseUnitTestKotlin SKIPPED
> Task :feature-qr:compileReleaseUnitTestKotlin NO-SOURCE
> Task :feature-qr:javaPreCompileReleaseUnitTest UP-TO-DATE
> Task :feature-qr:compileReleaseUnitTestJavaWithJavac NO-SOURCE
> Task :feature-qr:processReleaseUnitTestJavaRes NO-SOURCE
> Task :feature-qr:testReleaseUnitTest NO-SOURCE
> Task :feature-qr:test UP-TO-DATE
> Task :feature-quiz:preDebugUnitTestBuild UP-TO-DATE
> Task :feature-quiz:generateDebugUnitTestStubRFile UP-TO-DATE
> Task :feature-quiz:kaptGenerateStubsDebugUnitTestKotlin UP-TO-DATE
> Task :feature-quiz:kaptDebugUnitTestKotlin SKIPPED
> Task :feature-quiz:compileDebugUnitTestKotlin NO-SOURCE
> Task :feature-quiz:javaPreCompileDebugUnitTest UP-TO-DATE
> Task :feature-quiz:compileDebugUnitTestJavaWithJavac NO-SOURCE
> Task :feature-quiz:processDebugUnitTestJavaRes NO-SOURCE
> Task :feature-quiz:testDebugUnitTest NO-SOURCE
> Task :feature-quiz:preReleaseUnitTestBuild UP-TO-DATE
> Task :feature-quiz:generateReleaseUnitTestStubRFile UP-TO-DATE
> Task :feature-quiz:kaptGenerateStubsReleaseUnitTestKotlin UP-TO-DATE
> Task :feature-quiz:kaptReleaseUnitTestKotlin SKIPPED
> Task :feature-quiz:compileReleaseUnitTestKotlin NO-SOURCE
> Task :feature-quiz:javaPreCompileReleaseUnitTest UP-TO-DATE
> Task :feature-quiz:compileReleaseUnitTestJavaWithJavac NO-SOURCE
> Task :feature-quiz:processReleaseUnitTestJavaRes NO-SOURCE
> Task :feature-quiz:testReleaseUnitTest NO-SOURCE
> Task :feature-quiz:test UP-TO-DATE
> Task :feature-training:preDebugUnitTestBuild UP-TO-DATE
> Task :feature-training:generateDebugUnitTestStubRFile UP-TO-DATE
> Task :feature-training:kaptGenerateStubsDebugUnitTestKotlin UP-TO-DATE
> Task :feature-training:kaptDebugUnitTestKotlin SKIPPED
> Task :feature-training:compileDebugUnitTestKotlin NO-SOURCE
> Task :feature-training:javaPreCompileDebugUnitTest UP-TO-DATE
> Task :feature-training:compileDebugUnitTestJavaWithJavac NO-SOURCE
> Task :feature-training:processDebugUnitTestJavaRes NO-SOURCE
> Task :feature-training:testDebugUnitTest NO-SOURCE
> Task :feature-training:preReleaseUnitTestBuild UP-TO-DATE
> Task :feature-training:generateReleaseUnitTestStubRFile UP-TO-DATE
> Task :feature-training:kaptGenerateStubsReleaseUnitTestKotlin UP-TO-DATE
> Task :feature-training:kaptReleaseUnitTestKotlin SKIPPED
> Task :feature-training:compileReleaseUnitTestKotlin NO-SOURCE
> Task :feature-training:javaPreCompileReleaseUnitTest UP-TO-DATE
> Task :feature-training:compileReleaseUnitTestJavaWithJavac NO-SOURCE
> Task :feature-training:processReleaseUnitTestJavaRes NO-SOURCE
> Task :feature-training:testReleaseUnitTest NO-SOURCE
> Task :feature-training:test UP-TO-DATE

Deprecated Gradle features were used in this build, making it incompatible with Gradle 9.0.

You can use '--warning-mode all' to show the individual deprecation warnings and determine if they come from your own scripts or plugins.

For more on this, please refer to https://docs.gradle.org/8.10.2/userguide/command_line_interface.html#sec:command_line_warnings in the Gradle documentation.

BUILD SUCCESSFUL in 4s
402 actionable tasks: 402 up-to-date
  ✓ exit code 0

$ ./gradlew jacocoTestReport
> Task :app:checkKotlinGradlePluginConfigurationErrors SKIPPED
> Task :app:preBuild UP-TO-DATE
> Task :app:preDebugBuild UP-TO-DATE
> Task :core-database:preBuild UP-TO-DATE
> Task :core-database:preDebugBuild UP-TO-DATE
> Task :core-database:writeDebugAarMetadata UP-TO-DATE
> Task :core-mapping:preBuild UP-TO-DATE
> Task :core-mapping:preDebugBuild UP-TO-DATE
> Task :core-mapping:writeDebugAarMetadata UP-TO-DATE
> Task :core-navigation:preBuild UP-TO-DATE
> Task :core-navigation:preDebugBuild UP-TO-DATE
> Task :core-navigation:writeDebugAarMetadata UP-TO-DATE
> Task :core-security:preBuild UP-TO-DATE
> Task :core-security:preDebugBuild UP-TO-DATE
> Task :core-security:writeDebugAarMetadata UP-TO-DATE
> Task :core-storage:preBuild UP-TO-DATE
> Task :core-storage:preDebugBuild UP-TO-DATE
> Task :core-storage:writeDebugAarMetadata UP-TO-DATE
> Task :feature-map:preBuild UP-TO-DATE
> Task :feature-map:preDebugBuild UP-TO-DATE
> Task :feature-map:writeDebugAarMetadata UP-TO-DATE
> Task :feature-profile:preBuild UP-TO-DATE
> Task :feature-profile:preDebugBuild UP-TO-DATE
> Task :feature-profile:writeDebugAarMetadata UP-TO-DATE
> Task :feature-qr:preBuild UP-TO-DATE
> Task :feature-qr:preDebugBuild UP-TO-DATE
> Task :feature-qr:writeDebugAarMetadata UP-TO-DATE
> Task :feature-quiz:preBuild UP-TO-DATE
> Task :feature-quiz:preDebugBuild UP-TO-DATE
> Task :feature-quiz:writeDebugAarMetadata UP-TO-DATE
> Task :feature-training:preBuild UP-TO-DATE
> Task :feature-training:preDebugBuild UP-TO-DATE
> Task :feature-training:writeDebugAarMetadata UP-TO-DATE
> Task :app:checkDebugAarMetadata UP-TO-DATE
> Task :app:generateDebugResValues UP-TO-DATE
> Task :core-database:generateDebugResValues UP-TO-DATE
> Task :core-database:generateDebugResources UP-TO-DATE
> Task :core-database:packageDebugResources UP-TO-DATE
> Task :core-mapping:generateDebugResValues UP-TO-DATE
> Task :core-mapping:generateDebugResources UP-TO-DATE
> Task :core-mapping:packageDebugResources UP-TO-DATE
> Task :core-navigation:generateDebugResValues UP-TO-DATE
> Task :core-navigation:generateDebugResources UP-TO-DATE
> Task :core-navigation:packageDebugResources UP-TO-DATE
> Task :core-security:generateDebugResValues UP-TO-DATE
> Task :core-security:generateDebugResources UP-TO-DATE
> Task :core-security:packageDebugResources UP-TO-DATE
> Task :core-storage:generateDebugResValues UP-TO-DATE
> Task :core-storage:generateDebugResources UP-TO-DATE
> Task :core-storage:packageDebugResources UP-TO-DATE
> Task :feature-map:generateDebugResValues UP-TO-DATE
> Task :feature-map:generateDebugResources UP-TO-DATE
> Task :feature-map:packageDebugResources UP-TO-DATE
> Task :feature-profile:generateDebugResValues UP-TO-DATE
> Task :feature-profile:generateDebugResources UP-TO-DATE
> Task :feature-profile:packageDebugResources UP-TO-DATE
> Task :feature-qr:generateDebugResValues UP-TO-DATE
> Task :feature-qr:generateDebugResources UP-TO-DATE
> Task :feature-qr:packageDebugResources UP-TO-DATE
> Task :feature-quiz:generateDebugResValues UP-TO-DATE
> Task :feature-quiz:generateDebugResources UP-TO-DATE
> Task :feature-quiz:packageDebugResources UP-TO-DATE
> Task :feature-training:generateDebugResValues UP-TO-DATE
> Task :feature-training:generateDebugResources UP-TO-DATE
> Task :feature-training:packageDebugResources UP-TO-DATE
> Task :app:mapDebugSourceSetPaths UP-TO-DATE
> Task :app:generateDebugResources UP-TO-DATE
> Task :app:mergeDebugResources UP-TO-DATE
> Task :app:packageDebugResources UP-TO-DATE
> Task :app:parseDebugLocalResources UP-TO-DATE
> Task :app:createDebugCompatibleScreenManifests UP-TO-DATE
> Task :app:extractDeepLinksDebug UP-TO-DATE
> Task :core-database:extractDeepLinksDebug UP-TO-DATE
> Task :core-database:processDebugManifest UP-TO-DATE
> Task :core-mapping:extractDeepLinksDebug UP-TO-DATE
> Task :core-mapping:processDebugManifest UP-TO-DATE
> Task :core-navigation:extractDeepLinksDebug UP-TO-DATE
> Task :core-navigation:processDebugManifest UP-TO-DATE
> Task :core-security:extractDeepLinksDebug UP-TO-DATE
> Task :core-security:processDebugManifest UP-TO-DATE
> Task :core-storage:extractDeepLinksDebug UP-TO-DATE
> Task :core-storage:processDebugManifest UP-TO-DATE
> Task :feature-map:extractDeepLinksDebug UP-TO-DATE
> Task :feature-map:processDebugManifest UP-TO-DATE
> Task :feature-profile:extractDeepLinksDebug UP-TO-DATE
> Task :feature-profile:processDebugManifest UP-TO-DATE
> Task :feature-qr:extractDeepLinksDebug UP-TO-DATE
> Task :feature-qr:processDebugManifest UP-TO-DATE
> Task :feature-quiz:extractDeepLinksDebug UP-TO-DATE
> Task :feature-quiz:processDebugManifest UP-TO-DATE
> Task :feature-training:extractDeepLinksDebug UP-TO-DATE
> Task :feature-training:processDebugManifest UP-TO-DATE
> Task :app:processDebugMainManifest UP-TO-DATE
> Task :app:processDebugManifest UP-TO-DATE
> Task :app:processDebugManifestForPackage UP-TO-DATE
> Task :core-database:compileDebugLibraryResources UP-TO-DATE
> Task :core-database:parseDebugLocalResources UP-TO-DATE
> Task :core-database:generateDebugRFile UP-TO-DATE
> Task :core-mapping:compileDebugLibraryResources UP-TO-DATE
> Task :core-mapping:parseDebugLocalResources UP-TO-DATE
> Task :core-mapping:generateDebugRFile UP-TO-DATE
> Task :core-navigation:compileDebugLibraryResources UP-TO-DATE
> Task :core-navigation:parseDebugLocalResources UP-TO-DATE
> Task :core-navigation:generateDebugRFile UP-TO-DATE
> Task :core-security:compileDebugLibraryResources UP-TO-DATE
> Task :core-security:parseDebugLocalResources UP-TO-DATE
> Task :core-security:generateDebugRFile UP-TO-DATE
> Task :core-storage:compileDebugLibraryResources UP-TO-DATE
> Task :core-storage:parseDebugLocalResources UP-TO-DATE
> Task :core-storage:generateDebugRFile UP-TO-DATE
> Task :feature-map:compileDebugLibraryResources UP-TO-DATE
> Task :feature-map:parseDebugLocalResources UP-TO-DATE
> Task :feature-map:generateDebugRFile UP-TO-DATE
> Task :feature-profile:compileDebugLibraryResources UP-TO-DATE
> Task :feature-profile:parseDebugLocalResources UP-TO-DATE
> Task :feature-profile:generateDebugRFile UP-TO-DATE
> Task :feature-qr:compileDebugLibraryResources UP-TO-DATE
> Task :feature-qr:parseDebugLocalResources UP-TO-DATE
> Task :feature-qr:generateDebugRFile UP-TO-DATE
> Task :feature-quiz:compileDebugLibraryResources UP-TO-DATE
> Task :feature-quiz:parseDebugLocalResources UP-TO-DATE
> Task :feature-quiz:generateDebugRFile UP-TO-DATE
> Task :feature-training:compileDebugLibraryResources UP-TO-DATE
> Task :feature-training:parseDebugLocalResources UP-TO-DATE
> Task :feature-training:generateDebugRFile UP-TO-DATE
> Task :app:processDebugResources UP-TO-DATE
> Task :core-database:checkKotlinGradlePluginConfigurationErrors SKIPPED
> Task :core-database:kspDebugKotlin UP-TO-DATE
> Task :core-database:kaptGenerateStubsDebugKotlin UP-TO-DATE
> Task :core-database:kaptDebugKotlin SKIPPED
> Task :core-database:compileDebugKotlin UP-TO-DATE
> Task :core-database:javaPreCompileDebug UP-TO-DATE
> Task :core-database:compileDebugJavaWithJavac NO-SOURCE
> Task :core-database:bundleLibCompileToJarDebug UP-TO-DATE
> Task :core-mapping:checkKotlinGradlePluginConfigurationErrors SKIPPED
> Task :core-mapping:kaptGenerateStubsDebugKotlin UP-TO-DATE
> Task :core-mapping:kaptDebugKotlin SKIPPED
> Task :core-mapping:compileDebugKotlin NO-SOURCE
> Task :core-mapping:javaPreCompileDebug UP-TO-DATE
> Task :core-mapping:compileDebugJavaWithJavac NO-SOURCE
> Task :core-mapping:bundleLibCompileToJarDebug UP-TO-DATE
> Task :core-navigation:checkKotlinGradlePluginConfigurationErrors SKIPPED
> Task :core-navigation:kaptGenerateStubsDebugKotlin UP-TO-DATE
> Task :core-navigation:kaptDebugKotlin SKIPPED
> Task :core-navigation:compileDebugKotlin UP-TO-DATE
> Task :core-navigation:javaPreCompileDebug UP-TO-DATE
> Task :core-navigation:compileDebugJavaWithJavac NO-SOURCE
> Task :core-navigation:bundleLibCompileToJarDebug UP-TO-DATE
> Task :core-security:checkKotlinGradlePluginConfigurationErrors SKIPPED
> Task :core-security:kaptGenerateStubsDebugKotlin UP-TO-DATE
> Task :core-security:kaptDebugKotlin SKIPPED
> Task :core-security:compileDebugKotlin UP-TO-DATE
> Task :core-security:javaPreCompileDebug UP-TO-DATE
> Task :core-security:compileDebugJavaWithJavac NO-SOURCE
> Task :core-security:bundleLibCompileToJarDebug UP-TO-DATE
> Task :core-storage:checkKotlinGradlePluginConfigurationErrors SKIPPED
> Task :core-storage:kaptGenerateStubsDebugKotlin UP-TO-DATE
> Task :core-storage:kaptDebugKotlin SKIPPED
> Task :core-storage:compileDebugKotlin UP-TO-DATE
> Task :core-storage:javaPreCompileDebug UP-TO-DATE
> Task :core-storage:compileDebugJavaWithJavac NO-SOURCE
> Task :core-storage:bundleLibCompileToJarDebug UP-TO-DATE
> Task :feature-map:checkKotlinGradlePluginConfigurationErrors SKIPPED
> Task :feature-map:kaptGenerateStubsDebugKotlin UP-TO-DATE
> Task :feature-map:kaptDebugKotlin SKIPPED
> Task :feature-map:compileDebugKotlin UP-TO-DATE
> Task :feature-map:javaPreCompileDebug UP-TO-DATE
> Task :feature-map:compileDebugJavaWithJavac NO-SOURCE
> Task :feature-map:bundleLibCompileToJarDebug UP-TO-DATE
> Task :feature-profile:checkKotlinGradlePluginConfigurationErrors SKIPPED
> Task :feature-profile:kaptGenerateStubsDebugKotlin UP-TO-DATE
> Task :feature-profile:kaptDebugKotlin SKIPPED
> Task :feature-profile:compileDebugKotlin UP-TO-DATE
> Task :feature-profile:javaPreCompileDebug UP-TO-DATE
> Task :feature-profile:compileDebugJavaWithJavac NO-SOURCE
> Task :feature-profile:bundleLibCompileToJarDebug UP-TO-DATE
> Task :feature-qr:checkKotlinGradlePluginConfigurationErrors SKIPPED
> Task :feature-qr:kaptGenerateStubsDebugKotlin UP-TO-DATE
> Task :feature-qr:kaptDebugKotlin SKIPPED
> Task :feature-qr:compileDebugKotlin UP-TO-DATE
> Task :feature-qr:javaPreCompileDebug UP-TO-DATE
> Task :feature-qr:compileDebugJavaWithJavac NO-SOURCE
> Task :feature-qr:bundleLibCompileToJarDebug UP-TO-DATE
> Task :feature-quiz:checkKotlinGradlePluginConfigurationErrors SKIPPED
> Task :feature-quiz:kaptGenerateStubsDebugKotlin UP-TO-DATE
> Task :feature-quiz:kaptDebugKotlin SKIPPED
> Task :feature-quiz:compileDebugKotlin UP-TO-DATE
> Task :feature-quiz:javaPreCompileDebug UP-TO-DATE
> Task :feature-quiz:compileDebugJavaWithJavac NO-SOURCE
> Task :feature-quiz:bundleLibCompileToJarDebug UP-TO-DATE
> Task :feature-training:checkKotlinGradlePluginConfigurationErrors SKIPPED
> Task :feature-training:kaptGenerateStubsDebugKotlin UP-TO-DATE
> Task :feature-training:kaptDebugKotlin SKIPPED
> Task :feature-training:compileDebugKotlin UP-TO-DATE
> Task :feature-training:javaPreCompileDebug UP-TO-DATE
> Task :feature-training:compileDebugJavaWithJavac NO-SOURCE
> Task :feature-training:bundleLibCompileToJarDebug UP-TO-DATE
> Task :app:kaptGenerateStubsDebugKotlin UP-TO-DATE
> Task :app:kaptDebugKotlin SKIPPED
> Task :app:compileDebugKotlin UP-TO-DATE
> Task :app:javaPreCompileDebug UP-TO-DATE
> Task :app:compileDebugJavaWithJavac NO-SOURCE
> Task :app:bundleDebugClassesToRuntimeJar UP-TO-DATE
> Task :app:bundleDebugClassesToCompileJar UP-TO-DATE
> Task :app:kaptGenerateStubsDebugUnitTestKotlin UP-TO-DATE
> Task :app:kaptDebugUnitTestKotlin SKIPPED
> Task :app:compileDebugUnitTestKotlin NO-SOURCE
> Task :app:preDebugUnitTestBuild UP-TO-DATE
> Task :app:javaPreCompileDebugUnitTest UP-TO-DATE
> Task :app:compileDebugUnitTestJavaWithJavac NO-SOURCE
> Task :app:generateDebugJacocoPropertiesFile UP-TO-DATE
> Task :app:processDebugJavaRes UP-TO-DATE
> Task :app:processDebugUnitTestJavaRes NO-SOURCE
> Task :core-database:bundleLibRuntimeToJarDebug UP-TO-DATE
> Task :core-database:processDebugJavaRes UP-TO-DATE
> Task :core-mapping:bundleLibRuntimeToJarDebug UP-TO-DATE
> Task :core-mapping:processDebugJavaRes NO-SOURCE
> Task :core-navigation:bundleLibRuntimeToJarDebug UP-TO-DATE
> Task :core-navigation:processDebugJavaRes UP-TO-DATE
> Task :core-security:bundleLibRuntimeToJarDebug UP-TO-DATE
> Task :core-security:processDebugJavaRes UP-TO-DATE
> Task :core-storage:bundleLibRuntimeToJarDebug UP-TO-DATE
> Task :core-storage:processDebugJavaRes UP-TO-DATE
> Task :feature-map:bundleLibRuntimeToJarDebug UP-TO-DATE
> Task :feature-map:processDebugJavaRes UP-TO-DATE
> Task :feature-profile:bundleLibRuntimeToJarDebug UP-TO-DATE
> Task :feature-profile:processDebugJavaRes UP-TO-DATE
> Task :feature-qr:bundleLibRuntimeToJarDebug UP-TO-DATE
> Task :feature-qr:processDebugJavaRes UP-TO-DATE
> Task :feature-quiz:bundleLibRuntimeToJarDebug UP-TO-DATE
> Task :feature-quiz:processDebugJavaRes UP-TO-DATE
> Task :feature-training:bundleLibRuntimeToJarDebug UP-TO-DATE
> Task :feature-training:processDebugJavaRes UP-TO-DATE
> Task :app:testDebugUnitTest NO-SOURCE
> Task :app:jacocoTestReport SKIPPED

Deprecated Gradle features were used in this build, making it incompatible with Gradle 9.0.

You can use '--warning-mode all' to show the individual deprecation warnings and determine if they come from your own scripts or plugins.

For more on this, please refer to https://docs.gradle.org/8.10.2/userguide/command_line_interface.html#sec:command_line_warnings in the Gradle documentation.

BUILD SUCCESSFUL in 2s
171 actionable tasks: 171 up-to-date
  ✓ exit code 0

$ ./gradlew ktlintCheck detekt
> Task :app:loadKtlintReporters UP-TO-DATE
> Task :app:runKtlintCheckOverAndroidTestDebugSourceSet NO-SOURCE
> Task :app:ktlintAndroidTestDebugSourceSetCheck SKIPPED
> Task :app:runKtlintCheckOverAndroidTestReleaseSourceSet NO-SOURCE
> Task :app:ktlintAndroidTestReleaseSourceSetCheck SKIPPED
> Task :app:runKtlintCheckOverAndroidTestSourceSet NO-SOURCE
> Task :app:ktlintAndroidTestSourceSetCheck SKIPPED
> Task :app:runKtlintCheckOverDebugSourceSet NO-SOURCE
> Task :app:ktlintDebugSourceSetCheck SKIPPED
> Task :app:runKtlintCheckOverKotlinScripts UP-TO-DATE
> Task :app:ktlintKotlinScriptCheck UP-TO-DATE
> Task :app:runKtlintCheckOverMainSourceSet UP-TO-DATE
> Task :app:ktlintMainSourceSetCheck UP-TO-DATE
> Task :app:runKtlintCheckOverReleaseSourceSet NO-SOURCE
> Task :app:ktlintReleaseSourceSetCheck SKIPPED
> Task :app:runKtlintCheckOverTestDebugSourceSet NO-SOURCE
> Task :app:ktlintTestDebugSourceSetCheck SKIPPED
> Task :app:runKtlintCheckOverTestFixturesDebugSourceSet NO-SOURCE
> Task :app:ktlintTestFixturesDebugSourceSetCheck SKIPPED
> Task :app:runKtlintCheckOverTestFixturesReleaseSourceSet NO-SOURCE
> Task :app:ktlintTestFixturesReleaseSourceSetCheck SKIPPED
> Task :app:runKtlintCheckOverTestFixturesSourceSet NO-SOURCE
> Task :app:ktlintTestFixturesSourceSetCheck SKIPPED
> Task :app:runKtlintCheckOverTestReleaseSourceSet NO-SOURCE
> Task :app:ktlintTestReleaseSourceSetCheck SKIPPED
> Task :app:runKtlintCheckOverTestSourceSet NO-SOURCE
> Task :app:ktlintTestSourceSetCheck SKIPPED
> Task :app:ktlintCheck UP-TO-DATE
> Task :app:detekt UP-TO-DATE

Deprecated Gradle features were used in this build, making it incompatible with Gradle 9.0.

You can use '--warning-mode all' to show the individual deprecation warnings and determine if they come from your own scripts or plugins.

For more on this, please refer to https://docs.gradle.org/8.10.2/userguide/command_line_interface.html#sec:command_line_warnings in the Gradle documentation.

BUILD SUCCESSFUL in 1s
6 actionable tasks: 6 up-to-date
  ✓ exit code 0

$ ls -la app/src/main/assets/
total 8
drwxr-xr-x 2 yano4 yano4 4096 Apr 23 14:09 .
drwxr-xr-x 5 yano4 yano4 4096 Apr 24 15:41 ..
  ✓ exit code 0

$ adb shell am start -n com.safeplant/.MainActivity
adb: no devices/emulators found
  ✗ exit code 1

**LLM:** 
## Анализ результатов проверок

Я проанализировал результаты проверок и вижу несколько важных моментов:

### ✅ Успешные проверки
1. **Сборка проекта** (`./gradlew build`) - прошла успешно
2. **Запуск тестов** (`./gradlew test`) - прошел успешно (хотя тестов нет)
3. **Отчет Jacoco** (`./gradlew jacocoTestReport`) - прошел успешно (хотя тестов нет)
4. **Линтеры** (`./gradlew ktlintCheck detekt`) - прошли успешно
5. **Assets** - директория assets существует, но пуста

### ❌ Проблемы
1. **Нет тесто...

**Proposed files:** (no files)

---

## 2026-04-24 21:33

**User:** У меня приложение подключено, приложение открыто, почему пишется на skaro, что adb: no devices/emulators found

**LLM:** 
## Анализ проблемы с ADB

Проблема в том, что ADB не может найти подключенные устройства, хотя вы говорите, что приложение открыто. Это распространенная ситуация, которая может возникать по нескольким причинам.

### Возможные причины:

1. **Права доступа к ADB** - пользователь может не иметь прав на использование ADB
2. **Устройство не отладочное** - на устройстве может быть отключена отладка по USB
3. **Неправильный режим подключения** - устройство может быть в режиме зарядки без передачи данн...

**Proposed files:** (no files)
