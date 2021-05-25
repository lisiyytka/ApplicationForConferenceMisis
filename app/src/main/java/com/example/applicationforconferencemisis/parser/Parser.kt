//package com.example.applicationforconferencemisis.parser
//
//import android.app.Activity
//import android.content.Context
//import org.apache.poi.ss.usermodel.CellType
//import org.apache.poi.xssf.usermodel.XSSFWorkbook
//import java.io.File
//import java.io.FileInputStream
//import java.io.IOException
//import kotlin.jvm.Throws
//
//
//class Parserrr {
//    fun parse(FilePath: String, tableName: String) {
//        val excelFile = FileInputStream(File(FilePath))
//        val workbook = XSSFWorkbook(excelFile)
//        val sheet = workbook.getSheet(tableName)
//        val rows = sheet.iterator()
//        while (rows.hasNext()) {
//            val currentRow = rows.next()
//            val cellsInRow = currentRow.iterator()
//            while (cellsInRow.hasNext()){
//                val currentCell = cellsInRow.next()
//                print(currentCell.stringCellValue + " ")
//            }
//        }
//        println()
//        workbook.close()
//        excelFile.close()
//    }
//}