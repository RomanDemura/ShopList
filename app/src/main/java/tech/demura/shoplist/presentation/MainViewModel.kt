package tech.demura.shoplist.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import tech.demura.shoplist.data.ShopListRepositoryImpl
import tech.demura.shoplist.domain.DeleteShopItemUseCase
import tech.demura.shoplist.domain.EditShopItemUseCase
import tech.demura.shoplist.domain.GetShopListUseCase
import tech.demura.shoplist.domain.ShopItem

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = ShopListRepositoryImpl(application)

    private val getShopListUseCase = GetShopListUseCase(repository)
    private val deleteShopItemUseCase = DeleteShopItemUseCase(repository)
    private val editShopItemUseCase = EditShopItemUseCase(repository)

    val shopList = getShopListUseCase.getShopList()

    fun deleteShopItem(shopItem: ShopItem) {
        viewModelScope.launch {
            deleteShopItemUseCase.deleteShopItem(shopItem)
        }
    }

    fun changeEnableState(shopItem: ShopItem) {
        viewModelScope.launch {
            val newShopItem = shopItem.copy(enabled = !shopItem.enabled)
            editShopItemUseCase.editShopItem(newShopItem)
        }
    }
}