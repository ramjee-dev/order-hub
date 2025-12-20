package com.ramjee.order_service.service.impl;

import com.ramjee.order_service.dto.request.OrderRequestDto;
import com.ramjee.order_service.dto.response.OrderResponseDto;
import com.ramjee.order_service.entity.Order;
import com.ramjee.order_service.enums.OrderStatus;
import com.ramjee.order_service.exception.ResourceNotFoundException;
import com.ramjee.order_service.repository.OrderRepository;
import com.ramjee.order_service.service.OrderMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private OrderMapper orderMapper;

    @InjectMocks
    private OrderServiceImpl orderService;

    @Test
    void createOrder_shouldSaveOrderAndReturnResponse() {

        // Arrange
        OrderRequestDto requestDto = new OrderRequestDto(1L,500.0);
        Order order = new Order();
        Order savedOrder = new Order();
        OrderResponseDto responseDto = new OrderResponseDto(100L,1L,
                500.0, OrderStatus.CREATED, Instant.now());

        when(orderMapper.toEntity(requestDto)).thenReturn(order);
        when(orderRepository.save(order)).thenReturn(savedOrder);
        when(orderMapper.toResponseDto(savedOrder)).thenReturn(responseDto);

        // Act
        OrderResponseDto result = orderService.createOrder(requestDto);

        // Assert
        assertEquals(responseDto,result);
        assertNotNull(result);

        verify(orderMapper).toEntity(requestDto);
        verify(orderRepository).save(order);
        verify(orderMapper).toResponseDto(savedOrder);
        verifyNoMoreInteractions(orderMapper,orderRepository);

    }

    @Test
    void getOrderById_shouldReturnOrder_whenOrderExists() {
        // Arrange
        Long orderId = 1L;
        Order order = new Order();
        OrderResponseDto responseDto = new OrderResponseDto(orderId,10L,
                750.0,OrderStatus.CREATED,Instant.now());

        when(orderRepository.findById(orderId)).thenReturn(Optional.of(order));
        when(orderMapper.toResponseDto(order)).thenReturn(responseDto);
        
        // Act
        OrderResponseDto result = orderService.getOrderById(orderId);

        // Assert
        assertNotNull(result);
        assertEquals(responseDto,result);


        verify(orderRepository).findById(orderId);
        verify(orderMapper).toResponseDto(order);
        verifyNoMoreInteractions(orderMapper,orderRepository);

    }

    @Test
    void getOrderById_shouldThrowException_whenOrderNotFound() {

        // Arrange
        Long orderId = 99L;

        when(orderRepository.findById(orderId)).thenReturn(Optional.empty());

        // Act + Assert
        assertThrows(ResourceNotFoundException.class,()->{
            orderService.getOrderById(orderId);
        });

        verify(orderRepository).findById(orderId);
        verify(orderMapper,never()).toResponseDto(any());
    }

    @Test
    void getAllOrders_shouldReturnOrdersList() {

        // Arrange
        List<Order> orderList = List.of(new Order(),new Order());
        List<OrderResponseDto> orderResponseDtoList = List.of(
                new OrderResponseDto(1L,10L,750.0,OrderStatus.CREATED,Instant.now()),
                new OrderResponseDto(2L,12L,800.0,OrderStatus.CREATED,Instant.now()));
        when(orderRepository.findAll()).thenReturn(orderList);
        when(orderMapper.toResponseDtoList(orderList)).thenReturn(orderResponseDtoList);

        // Act
        List<OrderResponseDto> result = orderService.getAllorders();

        // Assert
        assertNotNull(result);
        assertEquals(2,result.size());

        verify(orderRepository).findAll();
        verify(orderMapper).toResponseDtoList(orderList);
        verifyNoMoreInteractions(orderMapper,orderRepository);
    }
}