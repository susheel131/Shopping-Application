-- Payment Service Dummy Data
-- Users: 5 test users
INSERT INTO payment_service_db.user (id, email, name) VALUES
(1, 'john@example.com', 'John Doe'),
(2, 'jane@example.com', 'Jane Smith'),
(3, 'robert@example.com', 'Robert Wilson'),
(4, 'emily@example.com', 'Emily Brown'),
(5, 'michael@example.com', 'Michael Johnson'),
(6, 'susan@example.com', 'Susan Miller'),
(7, 'david@example.com', 'David Anderson'),
(8, 'laura@example.com', 'Laura Martinez'),
(9, 'kevin@example.com', 'Kevin Thompson'),
(10, 'olivia@example.com', 'Olivia Taylor');

-- Payment Methods: 6 payment methods for users
INSERT INTO payment_methods (id, user_id, type, card_last_4, cardholder_name, is_default) VALUES
(1, 1, 'CREDIT_CARD', '1234', 'John Doe', true),
(2, 1, 'DEBIT_CARD', '5678', 'John Doe', false),
(3, 2, 'CREDIT_CARD', '9012', 'Jane Smith', true),
(4, 3, 'CREDIT_CARD', '3456', 'Robert Wilson', true),
(5, 4, 'DEBIT_CARD', '7890', 'Emily Brown', true),
(6, 5, 'CREDIT_CARD', '2468', 'Michael Johnson', true);

-- Payments: Various payment statuses
INSERT INTO payment_service_db.payment_method (details, type, user_id) VALUES
('Visa **** 1234', 'CARD', 1),
('Mastercard **** 9876', 'CARD', 2),
('Paytm Wallet', 'WALLET', 3),
('Google Pay - UPI ID: john@upi', 'ALTERNATIVE', 4),
('PhonePe Wallet', 'WALLET', 5),
('BNPL - Simpl Pay', 'BNPL', 6),
('RazorPay UPI - laura@bank', 'ALTERNATIVE', 7),
('Amazon Pay Wallet', 'WALLET', 8),
('Visa **** 4455', 'CARD', 9),
('BNPL - LazyPay', 'BNPL', 10);
