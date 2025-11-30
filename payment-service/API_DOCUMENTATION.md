# Payment Service API Documentation

## Overview
The Payment Service API provides comprehensive endpoints for managing payments, payment methods, and payment history in the Shopping Application.

## Base URL
http://localhost:8080/api/v1

## API Endpoints Summary

### 1. Payment Management Endpoints

**POST /api/v1/payments/process** - Process a payment transaction
**GET /api/v1/payments/{paymentId}** - Retrieve payment details
**GET /api/v1/payments/history/{userId}** - Get user payment history
**POST /api/v1/payments/{paymentId}/cancel** - Cancel pending payment

### 2. Payment Methods Endpoints

**POST /api/v1/payments/methods** - Add new payment method
**GET /api/v1/payments/methods/{userId}** - Get user payment methods
**PUT /api/v1/payments/methods/{methodId}** - Update payment method
**DELETE /api/v1/payments/methods/{methodId}** - Delete payment method
**PUT /api/v1/payments/methods/{methodId}/default** - Set as default method

### 3. Refund Endpoints

**POST /api/v1/payments/{paymentId}/refund** - Initiate refund
**GET /api/v1/payments/refunds/{refundId}** - Get refund status
**GET /api/v1/payments/{paymentId}/refunds** - Get refunds for payment

### 4. Payment Validation Endpoints

**POST /api/v1/payments/validate** - Validate payment details
**POST /api/v1/payments/verify-otp** - Verify OTP for payment

## Error Codes
- 400: BAD_REQUEST - Invalid parameters
- 401: UNAUTHORIZED - Missing authentication
- 404: NOT_FOUND - Resource not found
- 409: CONFLICT - State conflict
- 500: INTERNAL_ERROR - Server error

## Authentication
All endpoints require Bearer token: Authorization: Bearer {token}

## Rate Limiting
100 requests per minute per user
