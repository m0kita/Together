package ru.mokita.together.domain.repository

import ru.mokita.together.domain.model.RichText

interface RichTextRepository {
    fun getRichTexts() : List<RichText>
}