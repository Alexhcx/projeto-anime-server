package com.clienteservidor.animeserver.animeserver.dao.paymentdao;

import java.util.List;
import java.util.Optional;

import com.clienteservidor.animeserver.animeserver.models.PaymentModel;

public interface PaymentDAO {

  // Create
  PaymentModel save(PaymentModel payment);

  // Read
  Optional<PaymentModel> findById(Long id);
  Optional<PaymentModel> findByOrderId(Long orderId); // Buscar pagamento pelo ID do pedido
  List<PaymentModel> findByMetodoPagamento(String metodoPagamento); // Buscar pagamentos pelo método
  List<PaymentModel> findByStatusPagamento(String statusPagamento); // Buscar pagamentos pelo status
  List<PaymentModel> findAll();

  // Update
  PaymentModel update(PaymentModel payment);

  // Delete
  void deleteById(Long id);
}
