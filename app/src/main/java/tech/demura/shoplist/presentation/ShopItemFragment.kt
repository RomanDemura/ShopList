package tech.demura.shoplist.presentation

import android.content.Context
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
import tech.demura.shoplist.databinding.FragmentShopItemBinding
import tech.demura.shoplist.domain.ShopItem

class ShopItemFragment (): Fragment() {

    private var _binding: FragmentShopItemBinding? = null
    val binding: FragmentShopItemBinding
    get() = _binding ?: throw RuntimeException("Fragment shop item binding == null")

    companion object{
        private const val SCREEN_MODE = "screen_mode"
        private const val SHOP_ITEM_ID = "extra_shop_item_id"
        private const val MODE_ADD = "mode_add"
        private const val MODE_EDIT = "mode_edit"
        private const val MODE_UNKNOWN = ""

        fun newInstanceAddItem(): ShopItemFragment{
            return ShopItemFragment().apply {
                arguments = Bundle().apply {
                    putString(SCREEN_MODE, MODE_ADD)
                }
            }
        }

        fun newInstanceEditItem(shopItemId: Int): ShopItemFragment{
            return ShopItemFragment().apply {
                arguments = Bundle().apply {
                    putString(SCREEN_MODE, MODE_EDIT)
                    putInt(SHOP_ITEM_ID, shopItemId)
                }
            }
        }
    }

    private lateinit var viewModel: ShopItemViewModel

//    private lateinit var tilName: TextInputLayout
//    private lateinit var tilCount: TextInputLayout
//    private lateinit var etName: EditText
//    private lateinit var etCount: EditText
//    private lateinit var buttonSave: Button

    private lateinit var onEditingFinished: OnEditingFinished

    var screenMode: String = MODE_UNKNOWN
    var shopItemId: Int = ShopItem.UNDEFINED_ID

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnEditingFinished){
            onEditingFinished = context
        } else {
            throw java.lang.RuntimeException("Context must implement OnEditingFinished interface")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[ShopItemViewModel::class.java]
        parseParams()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentShopItemBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //initViews(view)
        addChangeTextListener()
        observeViewModel(view)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.saveButton.setOnClickListener{
            when (screenMode) {
                MODE_ADD -> viewModel.addShopItem( binding.etName.text.toString(),  binding.etCount.text.toString())
                MODE_EDIT -> viewModel.editShopItem( binding.etName.text.toString(),  binding.etCount.text.toString())
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    fun parseParams(){
        val args = requireArguments()
        if (!args.containsKey(SCREEN_MODE)){
            throw RuntimeException("Param screen mode is absent.")
        }
        val mode = args.getString(SCREEN_MODE)
        if (mode != MODE_ADD && mode != MODE_EDIT){
            throw RuntimeException("Unknown screen mode: $mode")
        }
        screenMode = mode
        if (screenMode == MODE_EDIT){
            if (!args.containsKey(SHOP_ITEM_ID)){
                throw RuntimeException("Param shop item id is absent")
            }
            shopItemId = args.getInt(SHOP_ITEM_ID, ShopItem.UNDEFINED_ID)
            if (shopItemId < 0) {
                throw RuntimeException("Wrong shop item id: $shopItemId")
            }
            viewModel.getShopItem(shopItemId)
        }
    }

//    fun initViews(view: View){
//        tilName = view.findViewById(R.id.tilName)
//        tilCount = view.findViewById(R.id.tilCount)
//        etName = view.findViewById(R.id.et_name)
//        etCount = view.findViewById(R.id.et_count)
//        buttonSave = view.findViewById(R.id.save_button)
//    }

    private fun observeViewModel(view: View){
        viewModel.errorInputName.observe(viewLifecycleOwner){
            val message = if (it){
                getString(R.string.error_input_name)
            } else {
                null
            }
            binding.tilName.error = message
        }

        viewModel.errorInputCount.observe(viewLifecycleOwner){
            val message = if (it){
                getString(R.string.error_input_count)
            } else {
                null
            }
            binding.tilCount.error = message
        }

        viewModel.shouldCloseScreen.observe(viewLifecycleOwner){
            onEditingFinished.onEditingFinished()
        }

        viewModel.shopItem.observe(viewLifecycleOwner){
            binding.etName.setText(it.name)
            binding.etCount.setText(it.count.toString())
        }
    }

    private fun addChangeTextListener(){
        binding.etName.addTextChangedListener( object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.resetErrorInputName()
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })

        binding.etCount.addTextChangedListener( object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.resetErrorInputCount()
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })
    }

}