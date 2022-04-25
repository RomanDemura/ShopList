package tech.demura.shoplist.presentation

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import tech.demura.shoplist.R
import tech.demura.shoplist.domain.ShopItem
import java.lang.RuntimeException

class ShopItemActivity : AppCompatActivity() {

//    private lateinit var viewModel: ShopItemViewModel
//
//    private lateinit var tilName: TextInputLayout
//    private lateinit var tilCount: TextInputLayout
//    private lateinit var etName: TextInputEditText
//    private lateinit var etCount: TextInputEditText
//    private lateinit var buttonSave: Button
//
//    var screenMode = MODE_UNKNOWN
//    var shopItemId = ShopItem.UNDEFINED_ID

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop_item)
//        parseIntent()
//        viewModel = ViewModelProvider(this)[ShopItemViewModel::class.java]
//        initViews()
//        addChangeTextListener()
//        launchRightMode()
//        observeViewModel()
    }

//    private fun observeViewModel(){
//        viewModel.errorInputName.observe(this){
//            val message = if (it){
//                getString(R.string.error_input_name)
//            } else {
//                null
//            }
//            tilName.error = message
//        }
//
//        viewModel.errorInputCount.observe(this){
//            val message = if (it){
//                getString(R.string.error_input_count)
//            } else {
//                null
//            }
//            tilCount.error = message
//        }
//
//        viewModel.shouldCloseScreen.observe(this){
//            finish()
//        }
//    }
//
//    private fun launchRightMode(){
//        when (screenMode){
//            EXTRA_ADD_MODE -> launchAddMode()
//            EXTRA_EDIT_MODE -> launchEditMode()
//        }
//    }
//
//    private fun addChangeTextListener(){
//        etName.addTextChangedListener( object: TextWatcher {
//            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//            }
//
//            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//                viewModel.resetErrorInputName()
//            }
//
//            override fun afterTextChanged(p0: Editable?) {
//            }
//        })
//
//        etCount.addTextChangedListener( object: TextWatcher {
//            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//            }
//
//            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//                viewModel.resetErrorInputCount()
//            }
//
//            override fun afterTextChanged(p0: Editable?) {
//            }
//        })
//    }
//
//    private fun launchAddMode(){
//        buttonSave.setOnClickListener{
//            viewModel.addShopItem(etName.text?.toString(), etCount.text?.toString())
//        }
//    }
//
//    private fun launchEditMode(){
//        viewModel.getShopItem(shopItemId)
//        viewModel.shopItem.observe(this){
//            etName.setText(it.name)
//            etCount.setText(it.count.toString())
//        }
//        buttonSave.setOnClickListener{
//            viewModel.editShopItem(etName.text?.toString(), etCount.text?.toString())
//        }
//    }
//
//    fun initViews(){
//        tilName = findViewById(R.id.tilName)
//        tilCount = findViewById(R.id.tilCount)
//        etName = findViewById(R.id.et_name)
//        etCount = findViewById(R.id.et_count)
//        buttonSave = findViewById(R.id.save_button)
//    }
//
//    fun parseIntent(){
//        if (!intent.hasExtra(EXTRA_SCREEN_MODE)){
//            throw RuntimeException("Param screen mode is absent.")
//        }
//        val mode = intent.getStringExtra(EXTRA_SCREEN_MODE)
//        if (mode != EXTRA_ADD_MODE && mode != EXTRA_EDIT_MODE){
//            throw RuntimeException("Unknown screen mode: $mode")
//        }
//        screenMode = mode
//        if (screenMode == EXTRA_EDIT_MODE){
//            if (!intent.hasExtra(EXTRA_SHOP_ITEM_ID)){
//                throw RuntimeException("Param shop item id is absent")
//            }
//            shopItemId = intent.getIntExtra(EXTRA_SHOP_ITEM_ID, ShopItem.UNDEFINED_ID)
//            if (shopItemId < 0){
//                throw RuntimeException("Wrong shop item id")
//            }
//        }
//    }
//
//    companion object{
//        private const val EXTRA_SCREEN_MODE = "extra_mode"
//        private const val EXTRA_SHOP_ITEM_ID = "extra_shop_item_id"
//        private const val EXTRA_ADD_MODE = "mode_add"
//        private const val EXTRA_EDIT_MODE = "mode_edit"
//        private const val MODE_UNKNOWN = ""
//
//        fun newIntentAddShopItem(context: Context): Intent{
//            val intent = Intent(context, ShopItemActivity::class.java)
//            intent.putExtra(EXTRA_SCREEN_MODE, EXTRA_ADD_MODE)
//            return intent
//        }
//
//        fun newIntentEditShopItem(context: Context, shopItemId: Int): Intent{
//            val intent = Intent(context, ShopItemActivity::class.java)
//            intent.putExtra(EXTRA_SCREEN_MODE, EXTRA_EDIT_MODE)
//            intent.putExtra(EXTRA_SHOP_ITEM_ID, shopItemId)
//            return intent
//        }
//    }
}