package mx.alan.practicakodemiamvvmpokemon_rickandmorty.data.models

data class ErrorResponse(
    val protocol: String?,
    val code: Int,
    var message: String = "",
    var url: String = ""
)