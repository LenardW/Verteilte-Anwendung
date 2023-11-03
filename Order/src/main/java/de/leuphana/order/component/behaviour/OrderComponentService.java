package de.leuphana.order.component.behaviour;

import org.springframework.stereotype.Service;

import de.leuphana.order.component.structure.ArticleComplaintRequest;
import de.leuphana.order.component.structure.OrderEntity;
import de.leuphana.order.component.structure.OrderPositionEntity;

@Service
public interface OrderComponentService {

	public Integer createOrder(Integer customerId);

	public Integer addOrderPositionToOrder(Integer orderId, Integer articleId, Float articlePrice,
			Integer articleQuantity);

	public OrderEntity getOrder(Integer orderId);

	public OrderPositionEntity getOrderPosition(Integer orderId, Integer positionId);

	public boolean removeOrderPosition(Integer orderId, Integer positionId);

	public boolean removeOrder(Integer orderId);

	public boolean addComplaint(ArticleComplaintRequest complaint);

}
