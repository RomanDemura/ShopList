package tech.demura.shoplist.data

import tech.demura.shoplist.domain.ShopItem
import tech.demura.shoplist.domain.ShopListRepository
import java.lang.RuntimeException

object ShopListRepositoryImpl: ShopListRepository {

    private var shopList = mutableListOf<ShopItem>()
    private var autoIncrement = 0

    override fun addShopItem(shopItem: ShopItem) {
        if (shopItem.id == ShopItem.UNDEFINED_ID){
            shopItem.id = autoIncrement++
        }
        shopList.add(shopItem)
    }

    override fun deleteShopItem(shopItem: ShopItem) {
        shopList.remove(shopItem)
    }

    override fun editShopItem(shopItem: ShopItem) {
        val oldElement = getShopItem(shopItem.id)
        deleteShopItem(oldElement)
        addShopItem(shopItem)
    }

    override fun getShopItem(shopItemId: Int): ShopItem {
        return shopList.find{
            it.id == shopItemId
        } ?: throw RuntimeException("Shop Item with $shopItemId id not founded")
    }

    override fun getShopList(): List<ShopItem> {
        return shopList.toList()
    }
}