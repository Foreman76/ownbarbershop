package ru.int24.ownbarbershop.models.domen

sealed class TypeCardService() {
    class GroupService(): TypeCardService()
    class ImageService(): TypeCardService()
    class ItemService(): TypeCardService()
}
