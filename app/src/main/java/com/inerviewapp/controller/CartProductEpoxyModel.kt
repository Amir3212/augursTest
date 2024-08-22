package com.inerviewapp.controller


data class CartProductEpoxyModel(
    val product: String,

    )/* : ViewBindingKotlinModel<CartViewBinding>(R.layout.cart_view) {
    override fun CartViewBinding.bind() {

        product.let { prod ->
            cartProduct = product
            Glide.with(App.instance).load(prod.thumbnail).into(imageView3)
            textView13.text = prod.price.appendDollar()
        }

        imageView7.setOnClickListener {
            cartIconClicked.onCartIconClicked(product.id)
        }
        imageView3.setOnClickListener {
            cartIconClicked.onProductItemClicked(product)
        }


    }
}*/