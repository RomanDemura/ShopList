package tech.demura.shoplist.presentation

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import tech.demura.shoplist.R
import tech.demura.shoplist.domain.ShopItem

class ShopItemFragment (var screenMode: String = MODE_UNKNOWN,
var shopItemId: Int = ShopItem.UNDEFINED_ID): Fragment() {

    private lateinit var viewModel: ShopItemViewModel

    private lateinit var tilName: TextInputLayout
    private lateinit var tilCount: TextInputLayout
    private lateinit var etName: EditText
    private lateinit var etCount: EditText
    private lateinit var buttonSave: Button



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_shop_item, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        parseParams()
        viewModel = ViewModelProvider(this)[ShopItemViewModel::class.java]
        initViews(view)
        addChangeTextListener()
        //launchRightMode()
        observeViewModel(view)

    }

    fun parseParams(){
        if (screenMode == MODE_UNKNOWN){
            throw RuntimeException("Param screen mode is absent.")
        }
        if (screenMode != MODE_ADD && screenMode != MODE_EDIT){
            throw RuntimeException("Unknown screen mode: $screenMode")
        }
        if (screenMode == MODE_EDIT){
            if (shopItemId == ShopItem.UNDEFINED_ID){
                throw RuntimeException("Param shop item id is absent")
            }
            if (shopItemId < 0){
                throw RuntimeException("Wrong shop item id")
            }
        }
    }

    fun initViews(view: View){
        tilName = view.findViewById(R.id.tilName)
        tilCount = view.findViewById(R.id.tilCount)
        etName = view.findViewById(R.id.et_name)
        etCount = view.findViewById(R.id.et_count)
        buttonSave = view.findViewById(R.id.save_button)
    }

    private fun observeViewModel(view: View){
        viewModel.errorInputName.observe(viewLifecycleOwner){
            val message = if (it){
                getString(R.string.error_input_name)
            } else {
                null
            }
            tilName.error = message
        }

        viewModel.errorInputCount.observe(viewLifecycleOwner){
            val message = if (it){
                getString(R.string.error_input_count)
            } else {
                null
            }
            tilCount.error = message
        }

        viewModel.shouldCloseScreen.observe(viewLifecycleOwner){
            activity?.onBackPressed()
        }
    }

//    private fun launchRightMode(){
//        val fragment = when (screenMode){
//            EXTRA_ADD_MODE -> launchAddMode()
//            EXTRA_EDIT_MODE -> launchEditMode()
//            else -> throw java.lang.RuntimeException("Unknown screen mode: $screenMode")
//        }
//
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

    private fun addChangeTextListener(){
        etName.addTextChangedListener( object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.resetErrorInputName()
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })

        etCount.addTextChangedListener( object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.resetErrorInputCount()
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })
    }



    companion object{
        private const val EXTRA_SCREEN_MODE = "extra_mode"
        private const val EXTRA_SHOP_ITEM_ID = "extra_shop_item_id"
        private const val MODE_ADD = "mode_add"
        private const val MODE_EDIT = "mode_edit"
        private const val MODE_UNKNOWN = ""

        fun newInstanceAddItem(): ShopItemFragment{
            return ShopItemFragment(MODE_ADD)
        }

        fun newInstanceEditItem(shopItemId: Int): ShopItemFragment{
            return ShopItemFragment(MODE_EDIT, shopItemId)
        }
    }
}