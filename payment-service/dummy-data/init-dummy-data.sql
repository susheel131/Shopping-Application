-- Payment Service Dummy Data
-- Users: 5 test users
INSERT INTO users (id, name, email) VALUES
(1, 'John Doe', 'john@example.com'),
(2, 'Jane Smith', 'jane@example.com'),
(3, 'Robert Wilson', 'robert@example.com'),
(4, 'Emily Brown', 'emily@example.com'),
(5, 'Michael Johnson', 'michael@example.com');

-- Payment Methods: 6 payment methods for users
INSERT INTO payment_methods (id, user_id, type, card_last_4, cardholder_name, is_default) VALUES
(1, 1, 'CREDIT_CARD', '1234', 'John Doe', true),
(2, 1, 'DEBIT_CARD', '5678', 'John Doe', false),
(3, 2, 'CREDIT_CARD', '9012', 'Jane Smith', true),
(4, 3, 'CREDIT_CARD', '3456', 'Robert Wilson', true),
(5, 4, 'DEBIT_CARD', '7890', 'Emily Brown', true),
(6, 5, 'CREDIT_CARD', '2468', 'Michael Johnson', true);

-- Payments: Various payment statuses
INSERT INTO payments (id, user_id, method_id, order_id, amount, currency, status, transaction_id) VALUES
('PAY-001', 1, 1, 101, 999.99, 'USD', 'COMPLETED', 'TXN-12345'),
('PAY-002', 2, 3, 102, 1499.50, 'USD', 'COMPLETED', 'TXN-12346'),
('PAY-003', 3, 4, 103, 599.00, 'USD', 'COMPLETED', 'TXN-12347'),
('PAY-004', 4, 5, 104, 2499.99, 'USD', 'COMPLETED', 'TXN-12348'),
('PAY-005', 5, 6, 105, 349.99, 'USD', 'COMPLETED', 'TXN-12349'),
('PAY-006', 1, 2, 106, 799.00, 'USD', 'COMPLETED', 'TXN-12350'),
('PAY-007', 2, 3, 107, 599.99, 'USD', 'COMPLETED', 'TXN-12351'),
('PAY-008', 3, 4, 108, 1299.50, 'USD', 'COMPLETED', 'TXN-12352'),
('PAY-PEND-001', 4, 5, 109, 449.00, 'USD', 'PENDING', 'TXN-PENDING-001'),
('PAY-FAIL-001', 5, 6, 110, 199.99, 'USD', 'FAILED', 'TXN-FAILED-001');

-- Refunds: Some refund records
INSERT INTO refunds (id, payment_id, amount, reason, status) VALUES
('REF-001', 'PAY-001', 999.99, 'ORDER_CANCELLED', 'COMPLETED'),
('REF-002', 'PAY-002', 1499.50, 'PRODUCT_DEFECTIVE', 'INITIATED'),
('REF-003', 'PAY-003', 599.00, 'WRONG_ITEM_RECEIVED', 'COMPLETED');
