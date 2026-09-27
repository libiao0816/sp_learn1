package com.libiao.lblearn1.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.spring.service.IService;
import com.libiao.lblearn1.common.utils.PageResult;
import com.libiao.lblearn1.domain.dto.cart.CartAddDTO;
import com.libiao.lblearn1.domain.dto.cart.CartPageDTO;
import com.libiao.lblearn1.domain.dto.product.ProductPageDTO;
import com.libiao.lblearn1.domain.po.Cart;

import java.util.List;

public interface CartService extends IService<Cart> {

    int addCart(CartAddDTO cartAddDTO);

    int removeCart(List<Long> ids);

    PageResult<Cart> getCartPage(CartPageDTO cartPageDTO);
}
