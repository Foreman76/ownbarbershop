package ru.int24.ownbarbershop.utilits

import ru.int24.ownbarbershop.models.data.*
import ru.int24.ownbarbershop.models.domen.DomRecordCompany
import ru.int24.ownbarbershop.models.domen.DomRecordServices
import ru.int24.ownbarbershop.models.domen.DomRecordStaff
import ru.int24.ownbarbershop.models.domen.DomRecords
import java.util.*
import kotlin.collections.ArrayList


fun RecordsNet.toDomModel(typeRecord: TypeRecord):ArrayList<DomRecords>{

    val listDomRecords = arrayListOf<DomRecords>()
    this.data?.let { listDataRecords ->
        listDataRecords.forEach {

            if (it.deleted == false) {

               val domRecords: DomRecords? = getDomRecords(typeRecord, it)
               domRecords?.let {
                  listDomRecords.add(it)
               }

            }
        }
    }

    return listDomRecords

}

fun getDomRecords(typeRecord: TypeRecord, dataRecordsNet: DataRecordsNet): DomRecords? {
    if (typeRecord is TypeRecord.FutureRecords){
        return getFutureRecord(dataRecordsNet)
    }
    if (typeRecord is TypeRecord.Pastrecord) {
        return getPastRecord(dataRecordsNet)
    }
    return null
}

fun getPastRecord(dataRecordsNet: DataRecordsNet): DomRecords? {
    val currentDate = Date()
    val recordDate  = GetDataFormat.getDateFromString(dataRecordsNet.datetime)
    when (currentDate.after(recordDate)) {
        true -> { return  DomRecords(
                id = dataRecordsNet.id,
                services = getListServices(dataRecordsNet.services),
                company = getDomCompany(dataRecordsNet.company),
                staff = getDomrecordStaff(dataRecordsNet.staff),
                date = dataRecordsNet.date,
                datetime = dataRecordsNet.datetime,
                deleted = dataRecordsNet.deleted
        )}
    }
    return null
}

fun getFutureRecord(dataRecordsNet: DataRecordsNet): DomRecords? {
    val currentDate = Date()
    val recordDate  = GetDataFormat.getDateFromString(dataRecordsNet.datetime)
    when (currentDate.before(recordDate)) {
        true -> {
            return DomRecords(
                    id = dataRecordsNet.id,
                    services = getListServices(dataRecordsNet.services),
                    company = getDomCompany(dataRecordsNet.company),
                    staff = getDomrecordStaff(dataRecordsNet.staff),
                    date = dataRecordsNet.date,
                    datetime = dataRecordsNet.datetime,
                    deleted = dataRecordsNet.deleted
            )
        }
    }
    return null
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
            site = recordCompany.site,
            currency_short_title = recordCompany.currency_short_title
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

