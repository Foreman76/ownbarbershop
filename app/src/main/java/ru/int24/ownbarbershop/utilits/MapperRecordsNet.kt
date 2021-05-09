package ru.int24.ownbarbershop.utilits

import ru.int24.ownbarbershop.models.data.RecordCompany
import ru.int24.ownbarbershop.models.data.RecordServices
import ru.int24.ownbarbershop.models.data.RecordStaff
import ru.int24.ownbarbershop.models.data.RecordsNet
import ru.int24.ownbarbershop.models.domen.DomRecordCompany
import ru.int24.ownbarbershop.models.domen.DomRecordServices
import ru.int24.ownbarbershop.models.domen.DomRecordStaff
import ru.int24.ownbarbershop.models.domen.DomRecords


fun RecordsNet.toDomModel():DomRecords{

    return DomRecords(
            id = this.id,
            services = getListServices(this.services),
            company = getDomCompany(this.company),
            staff = getDomrecordStaff(this.staff),
            date = this.date,
            datetime = this.datetime,
            deleted = this.deleted,
            currency_short_title = this.currency_short_title
    )

}

fun getDomrecordStaff(recordStaff: RecordStaff): DomRecordStaff {
    return DomRecordStaff(
            id = recordStaff.id,
            name = recordStaff.name,
            specialization = recordStaff.specialization
    )
}

fun getDomCompany(recordCompany: RecordCompany): DomRecordCompany {
    return DomRecordCompany(
            id = recordCompany.id,
            title = recordCompany.title,
            city = recordCompany.city,
            timezone = recordCompany.timezone,
            address = recordCompany.address,
            phone = recordCompany.phone,
            coordinate_lat = recordCompany.coordinate_lat,
            coordinate_lng = recordCompany.coordinate_lng,
            site = recordCompany.site
    )
}

fun getListServices(listRecordServices: ArrayList<RecordServices>): ArrayList<DomRecordServices> {

   val listDomServices = ArrayList<DomRecordServices>()
   listRecordServices.forEach { recordService ->
       listDomServices.add(DomRecordServices(
               id = recordService.id,
               title = recordService.title,
               cost = recordService.cost
       ))
   }

    return listDomServices
}

