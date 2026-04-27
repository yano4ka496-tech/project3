#!/usr/bin/env python3
import os
import sys
import subprocess

def convert_pdf_to_mbtiles(pdf_path, output_path):
    # Пример: использование ogr2ogr для конвертации PDF слоёв в GeoJSON, затем в MBTiles
    print(f"Конвертация {pdf_path} в {output_path}")
    # Шаг 1: Извлечение слоёв из PDF в GeoJSON
    subprocess.run([
        "ogr2ogr", "-f", "GeoJSON", "temp_layers.geojson", pdf_path
    ], check=True)
    # Шаг 2: Преобразование GeoJSON в MBTiles (используя tippecanoe)
    subprocess.run([
        "tippecanoe", "-o", output_path, "temp_layers.geojson"
    ], check=True)
    # Шаг 3: Удаление временных файлов
    os.remove("temp_layers.geojson")
    print(f"Готово: {output_path}")

if __name__ == "__main__":
    if len(sys.argv) != 3:
        print("Использование: python3 convert_pdf_to_mbtiles.py <input.pdf> <output.mbtiles>")
        sys.exit(1)
    convert_pdf_to_mbtiles(sys.argv[1], sys.argv[2])
