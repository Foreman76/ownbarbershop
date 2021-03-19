package ru.int24.ownbarbershop.utilits

import ru.int24.ownbarbershop.config.DefConfig
import ru.int24.ownbarbershop.models.domen.DomServices
import ru.int24.ownbarbershop.models.domen.DomStaff
import ru.int24.ownbarbershop.models.domen.ParamForService
import ru.int24.ownbarbershop.models.domen.ParamForStaff

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

    }

}



