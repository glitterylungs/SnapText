package com.example.snaptext.mappers

internal interface Mapper<I, O> {

    fun map(input: I): O
}