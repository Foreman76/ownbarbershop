package ru.int24.ownbarbershop.network


import ru.int24.ownbarbershop.config.DefConfig
import ru.int24.ownbarbershop.models.domen.DomServices
import ru.int24.ownbarbershop.models.domen.TypeCardService
import ru.int24.ownbarbershop.models.data.Category
import ru.int24.ownbarbershop.models.data.Services
import ru.int24.ownbarbershop.models.data.ServicesNet

fun getHeaders(authUser: Boolean): Map<String, String> {

    val mapHeaders: Map<String, String>
    val authString:String
    when (authUser){
        true -> authString = "Bearer ${DefConfig.api}, User ${DefConfig.user_token}"
        else -> authString = "Bearer ${DefConfig.api}"
    }
    mapHeaders = mapOf("Content-type" to "application/json",
                       "Accept" to "application/vnd.yclients.v2+json",
                       "Authorization" to authString)
    return mapHeaders
}

//mapper ServicesNet.toDomModel()
fun ServicesNet.toDomModel(): MutableList<DomServices> {

    val listDomServices = mutableListOf<DomServices>()
    val listGroup: List<Category> = this.data.category
    val listServ: List<Services>  = this.data.services

    listGroup.forEach {
        listDomServices.add(DomServices(
                type_card = TypeCardService.GroupService(),
                id = it.id,
                category_id = it.id,
                title = it.title,
                ))

        val filterCatId = listServ.filter { v -> v.category_id == it.id }

        filterCatId.forEach{ v -> listDomServices.add(mapperServiceToDomService(v))}
    }
    listDomServices.forEach { v ->
        val position: Int = listDomServices.indexOf(v)
        v.position = position
    }
    return listDomServices

}

fun mapperServiceToDomService(model: Services): DomServices {
    lateinit var typeItem: TypeCardService
    if (model.image.isNotEmpty()){
        typeItem = TypeCardService.ImageService()
    }else typeItem = TypeCardService.ItemService()

    return DomServices(
                id = model.id,
                title = model.title,
                category_id = model.category_id,
                price_min = model.price_min,
                price_max = model.price_max,
                discount = model.discount,
                comment = model.comment,
                weight = model.weight,
                active = model.active,
                sex = model.sex,
                image = model.image,
                prepaid = model.prepaid,
                seance_length = model.seance_length,
                type_card = typeItem
    )
}