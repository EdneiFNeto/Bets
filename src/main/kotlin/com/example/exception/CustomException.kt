package com.example.exception

open class CustomException(error: String): Throwable(error)

class InvalidCPF: CustomException("CPF inválido")
class InvalidName: CustomException("CPF inválido")
class InvalidNumber(number: String): CustomException("Números $number inválido")
class InvalidRange(range: String): CustomException("Prêmio $range inválido")
class InvalidHour(hour: String): CustomException("Hora $hour inválido")