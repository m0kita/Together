package ru.mokita.together.data

import ru.mokita.together.data.source.database.model.DatabaseNote
import ru.mokita.together.data.source.network.model.DataCommunityNote
import ru.mokita.together.data.source.network.model.DataCourse
import ru.mokita.together.data.source.network.model.DataMessage
import ru.mokita.together.data.source.network.model.DataRichText
import ru.mokita.together.domain.model.CommunityNote
import ru.mokita.together.domain.model.Course
import ru.mokita.together.domain.model.Message
import ru.mokita.together.domain.model.Note
import ru.mokita.together.domain.model.RichText
import ru.mokita.together.domain.repository.CourseRepository

fun Note.toData() = DatabaseNote(
    id = this.id,
    title = this.title,
    description = this.description,
    date = this.date
)
@JvmName("fromUiToDataNote")
fun List<Note>.toData() = map(Note::toData)

fun DatabaseNote.toUi() = Note(
    id = this.id,
    title = this.title,
    description = this.description,
    date = this.date
)

@JvmName("fromDataToUiNote")
fun List<DatabaseNote>.toUi() = map(DatabaseNote::toUi)

fun DataCourse.toUi() = Course(
    title = title,
    tags = tags
)

@JvmName("fromDataToUiCourse")
fun List<DataCourse>.toUi() = map(DataCourse::toUi)

fun DataCommunityNote.toUi() = CommunityNote(
    title = title,
    id = id,
    content = content.toUi(),
    author = author,
    date = date,
    comments = comments.toUi()
)

@JvmName("fromDataToUiCommunityNote")
fun List<DataCommunityNote>.toUi() = map(DataCommunityNote::toUi)

fun DataRichText.toUi() = RichText(
    text = text,
    image = image
)

@JvmName("fromDataToUiRichText")
fun List<DataRichText>.toUi() = map(DataRichText::toUi)

fun RichText.toData() = DataRichText(
    text = text,
    image = image
)

@JvmName("fromUiToDataRichText")
fun List<RichText>.toData() = map(RichText::toData)

fun DataMessage.toUi() = Message(
    id = id,
    author = author,
    text = text,
    attachments = attachments,
    status = status
)

@JvmName("fromDataToUiMessage")
fun List<DataMessage>.toUi() = map(DataMessage::toUi)