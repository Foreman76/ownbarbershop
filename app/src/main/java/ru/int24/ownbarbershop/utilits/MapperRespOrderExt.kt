package ru.int24.ownbarbershop.utilits

import ru.int24.ownbarbershop.models.data.OrderNet
import ru.int24.ownbarbershop.models.data.RespOrderNet
import ru.int24.ownbarbershop.models.domen.DomRespOrder

fun RespOrderNet.toDomModel():ArrayList<DomRespOrder>{

    val listDomRespOrder = arrayListOf<DomRespOrder>()

    this.data?.let {
        it.forEach { itemOrderNet -> listDomRespOrder.add(respOrderNetToDomModel(itemOrderNet)) }
    }
    return listDomRespOrder
}

fun respOrderNetToDomModel(item: OrderNet):DomRespOrder{
    return DomRespOrder(
        id = item.id,
        record_id = item.record_id,
        record_hash = item.record_hash
    )
}