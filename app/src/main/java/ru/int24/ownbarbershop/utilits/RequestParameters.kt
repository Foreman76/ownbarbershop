package ru.int24.ownbarbershop.utilits

import ru.int24.ownbarbershop.config.DefConfig
import ru.int24.ownbarbershop.models.domen.*

class RequestParameters {

    companion object{
        fun makeParametersForStaff(listserv:MutableList<DomServices>?): ParamForStaff {
            val serviceIds:MutableList<String>? = listserv?.let { v ->
                v.mapTo(mutableListOf()){ it.id.toString()}
            }
            return ParamForStaff(companyid = DefConfig.id, datetime = null , service_ids = serviceIds)
        }

        fun makeParametersForService(staff:DomStaff?): ParamForService {
            val idStaff: Int? = staff?.let { staff.id }
            return ParamForService(staff_id = idStaff, companyid = DefConfig.id, datetime = null, service_ids = null)
        }

        fun makeParametersForSession(sessionDate:String, staff:DomStaff?, listService: MutableList<DomServices>?): ParamForSession{
            var idStaff:Int = 0
            when {
                staff != null -> idStaff = staff.id
            }
            val serviceIds:MutableList<String>? = listService?.let { v ->
                v.mapTo(mutableListOf()){ it.id.toString()}
            }
            return ParamForSession(staff_id = idStaff, companyid = DefConfig.id, datetime = sessionDate.substringBefore('T'),
                                    service_ids = serviceIds)
        }

        fun makeParametersForWorkDays(staff: DomStaff?, listService: MutableList<DomServices>?):ParamForWorkDays{
            var idStaff:Int = 0
            when {
                staff != null -> idStaff = staff.id
            }
            val serviceIds:MutableList<String>? = listService?.let { v ->
                v.mapTo(mutableListOf()){ it.id.toString()}
            }
            return ParamForWorkDays(staff_id = idStaff, companyid = DefConfig.id, service_ids = serviceIds)
        }

        fun makeParametersForGetSMSCode(): ParamGetSMS{
            return ParamGetSMS(companyid = DefConfig.id)
        }
    }

}



