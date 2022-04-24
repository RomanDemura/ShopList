package tech.demura.shoplist.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import tech.demura.shoplist.domain.ShopItem
import tech.demura.shoplist.domain.ShopListRepository
import java.lang.RuntimeException
import kotlin.random.Random

object ShopListRepositoryImpl: ShopListRepository {

    private var shopListLD = MutableLiveData<List<ShopItem>>()
    private var shopList = sortedSetOf<ShopItem>({ o1, o2 -> o1.id.compareTo(o2.id)})
    private var autoIncrement = 0

    init {
        for (i in 0 until 50){
            val item = ShopItem("$i", i, Random.nextBoolean())
            addShopItem(item)
        }
        updateLD()
    }

    override fun addShopItem(shopItem: ShopItem) {
        if (shopItem.id == ShopItem.UNDEFINED_ID){
            shopItem.id = autoIncrement++
        }
        shopList.add(shopItem)
        updateLD()
    }

    override fun deleteShopItem(shopItem: ShopItem) {
        shopList.remove(shopItem)
        updateLD()
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

    override fun getShopList(): LiveData<List<ShopItem>> {
        return shopListLD
    }

    private fun updateLD(){
        shopListLD.value = shopList.toList()
    }
}