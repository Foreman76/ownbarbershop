package ru.int24.ownbarbershop.utilits

import ru.int24.ownbarbershop.models.data.StaffNet
import ru.int24.ownbarbershop.models.db.DBStaff
import ru.int24.ownbarbershop.models.domen.DomStaff

fun StaffNet.toDomModel():MutableList<DomStaff>{
    val listDomStaff: MutableList<DomStaff> = mutableListOf()
    val listNetStaff = this.data

    listNetStaff.forEach{ staff->

        listDomStaff.add(DomStaff(
                id = staff.id,
                name = staff.name,
                specialization = staff.specialization,
                bookable = staff.bookable,
                weight = staff.weight,
                show_rating = staff.show_rating,
                rating = staff.rating,
                votes_count = staff.votes_count,
                comments_count = staff.comments_count,
                api_id =staff.api_id?.toString() ?: "",
                avatar = staff.avatar,
                avatar_big = staff.avatar_big,
                information = staff.information,
                position_id = staff.position_id,
                schedule_till = staff.schedule_till,
                fired = staff.fired,
                status = staff.status,
                hidden = staff.hidden,
                prepaid = staff.prepaid,
                user = staff.user?.toString() ?: ""
        ))
    }
    return listDomStaff

}

fun DomStaff.toDBStaff():DBStaff {
    return DBStaff(
            id = this.id,
            name = this.name,
            specialization = this.specialization,
            bookable = this.bookable,
            weight = this.weight,
            show_rating = this.show_rating,
            rating = this.rating,
            votes_count = this.votes_count,
            comments_count = this.comments_count,
            api_id =this.api_id,
            avatar = this.avatar,
            avatar_big = this.avatar_big,
            information = this.information,
            position_id = this.position_id,
            schedule_till = this.schedule_till,
            fired = this.fired,
            status = this.status,
            hidden = this.hidden,
            prepaid = this.prepaid,
            user = this.user
    )

}

fun DBStaff.toDomStaff(): DomStaff {
    return DomStaff(
            id = this.id,
            name = this.name,
            specialization = this.specialization,
            bookable = this.bookable,
            weight = this.weight,
            show_rating = this.show_rating,
            rating = this.rating,
            votes_count = this.votes_count,
            comments_count = this.comments_count,
            api_id =this.api_id,
            avatar = this.avatar,
            avatar_big = this.avatar_big,
            information = this.information,
            position_id = this.position_id,
            schedule_till = this.schedule_till,
            fired = this.fired,
            status = this.status,
            hidden = this.hidden,
            prepaid = this.prepaid,
            user = this.user
    )
}