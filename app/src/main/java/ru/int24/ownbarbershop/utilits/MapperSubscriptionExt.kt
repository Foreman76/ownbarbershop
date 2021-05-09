package ru.int24.ownbarbershop.utilits

import ru.int24.ownbarbershop.models.data.SubNet
import ru.int24.ownbarbershop.models.data.SubscriptionNet
import ru.int24.ownbarbershop.models.domen.DomSubscription

fun SubscriptionNet.toDomModel(): MutableList<DomSubscription>{
    if (this.meta.count != 0) {
        val listSubscription = mutableListOf<DomSubscription>()
        this.data.forEach{ subData ->
            listSubscription.add(SubDataToDomSubScription(subData))}
        return listSubscription
    }
    return mutableListOf()
}

fun SubDataToDomSubScription(subData:SubNet):DomSubscription{
    return DomSubscription(
            id = subData.id,
            number = subData.number,
            balance_string = subData.balance_string,
            is_frozen = subData.is_frozen,
            freeze_period = subData.freeze_period,
            period = subData.period,
            period_unit_id = subData.period_unit_id,
            expiration_date = subData.expiration_date
    )

}