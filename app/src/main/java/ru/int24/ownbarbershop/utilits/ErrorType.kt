package ru.int24.ownbarbershop.utilits

sealed class ErrorType {
    class Network():ErrorType()
    class Timeout():ErrorType()
    class Session_Expired():ErrorType()
    class Unknown():ErrorType()
}
