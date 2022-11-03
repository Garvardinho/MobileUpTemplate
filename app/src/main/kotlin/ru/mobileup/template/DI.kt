package ru.mobileup.template

import ru.mobileup.template.core.coreModule
import ru.mobileup.template.features.crypto.coinsModule
import ru.mobileup.template.features.pokemons.pokemonsModule

val allModules = listOf(
    coreModule(BuildConfig.BACKEND_URL),
    coinsModule,
    pokemonsModule
)