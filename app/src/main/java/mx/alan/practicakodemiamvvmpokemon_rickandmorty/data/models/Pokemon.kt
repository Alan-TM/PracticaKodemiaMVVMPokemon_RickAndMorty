package mx.alan.practicakodemiamvvmpokemon_rickandmorty.data.models

import com.google.gson.annotations.SerializedName

data class Pokemon(
    @SerializedName("name" ) var name : String? = null,
    @SerializedName("url"  ) var url  : String? = null
)
