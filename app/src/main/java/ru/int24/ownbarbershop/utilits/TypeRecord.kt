package ru.int24.ownbarbershop.utilits

sealed class TypeRecord() {
    class FutureRecords() :TypeRecord()
    class Pastrecord(): TypeRecord()
}
