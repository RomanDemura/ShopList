package tech.demura.shoplist.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import tech.demura.shoplist.data.ShopListRepositoryImpl
import tech.demura.shoplist.domain.DeleteShopItemUseCase
import tech.demura.shoplist.domain.EditShopItemUseCase
import tech.demura.shoplist.domain.GetShopListUseCase
import tech.demura.shoplist.domain.ShopItem

class MainViewModel : ViewModel() {

    private val repository = ShopListRepositoryImpl

    private val getShopListUseCase = GetShopListUseCase(repository)
    private val deleteShopItemUseCase = DeleteShopItemUseCase(repository)
    private val editShopItemUseCase = EditShopItemUseCase(repository)

    val shopList = getShopListUseCase.getShopList()

    fun deleteShopItem(shopItem: ShopItem) {
        deleteShopItemUseCase.deleteShopItem(shopItem)
    }

    fun changeEnableState(shopItem: ShopItem) {
        val newShopItem = shopItem.copy(enabled = !shopItem.enabled)
        editShopItemUseCase.editShopItem(newShopItem)
    }
}