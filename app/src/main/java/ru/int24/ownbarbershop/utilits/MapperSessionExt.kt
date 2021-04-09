package ru.int24.ownbarbershop.utilits

import ru.int24.ownbarbershop.models.data.SessionNet
import ru.int24.ownbarbershop.models.db.DBSession
import ru.int24.ownbarbershop.models.domen.DomSession

fun SessionNet.toDomModel(): MutableList<DomSession>{
    val listDomSession:MutableList<DomSession> = mutableListOf()

    this.data.forEach{ sessionTime ->
        listDomSession.add(DomSession(
                time = sessionTime.time,
                seance_length = sessionTime.seance_length,
                sum_length = sessionTime.sum_length,
                datetime = sessionTime.datetime
        ))
    }
    return listDomSession
}

fun DBSession.toDomSession(): DomSession {
    return DomSession(time = "",
            seance_length = 0,
            sum_length = 0,
            datetime = this.datetime)
}

fun DomSession.toDBSession():DBSession {
    return DBSession(datetime = this.datetime)
}