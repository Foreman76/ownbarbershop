package ru.int24.ownbarbershop.utilits

import ru.int24.ownbarbershop.models.data.Category
import ru.int24.ownbarbershop.models.data.Services
import ru.int24.ownbarbershop.models.data.ServicesNet
import ru.int24.ownbarbershop.models.db.DBService
import ru.int24.ownbarbershop.models.domen.DomServices
import ru.int24.ownbarbershop.models.domen.TypeCardService


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

fun DBService.toDomService(): DomServices{
    var typeCardService:TypeCardService = TypeCardService.ItemService()
    when (this.type_card) {
        "image" -> typeCardService = TypeCardService.ImageService()
        "group" -> typeCardService = TypeCardService.GroupService()
    }

    return DomServices(
            id = this.id,
            title = this.title,
            category_id = this.category_id,
            price_min = this.price_min,
            price_max = this.price_max,
            discount = this.discount,
            comment = this.comment,
            weight = this.weight,
            active = this.active,
            sex = this.sex,
            image = this.image,
            prepaid = this.prepaid,
            seance_length = this.seance_length,
            position = this.position,
            type_card = typeCardService


    )
}

fun DomServices.toDBService(): DBService{
    var typeCardService: String = "item"
    when (this.type_card) {
        is TypeCardService.ImageService -> typeCardService = "image"
        is TypeCardService.GroupService -> typeCardService = "group"
    }
    return DBService(
            id = this.id,
            title = this.title,
            category_id = this.category_id,
            price_min = this.price_min,
            price_max = this.price_max,
            discount = this.discount,
            comment = this.comment,
            weight = this.weight,
            active = this.active,
            sex = this.sex,
            image = this.image,
            prepaid = this.prepaid,
            seance_length = this.seance_length,
            position = this.position,
            type_card = typeCardService

    )
}