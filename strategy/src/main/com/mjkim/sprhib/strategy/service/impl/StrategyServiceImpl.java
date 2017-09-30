package com.mjkim.sprhib.strategy.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mjkim.sprhib.strategy.dao.StrategyDAO;
import com.mjkim.sprhib.strategy.model.Strategy;
import com.mjkim.sprhib.strategy.service.StrategyService;

@Service
@Transactional
public class StrategyServiceImpl implements StrategyService {

	@Autowired
	private StrategyDAO strategyDAO;

	public void addStrategy(Strategy strategy) {
		strategyDAO.addStrategy(strategy);
	}

	public void updateStrategy(Strategy strategy) {
		strategyDAO.updateStrategy(strategy);
	}

	public Strategy getStrategy(int id) {
		return strategyDAO.getStrategy(id);
	}

	public void deleteStrategy(int id) {
		strategyDAO.deleteStrategy(id);
	}

	public List<Strategy> getStrategies() {
		return strategyDAO.getStrategies();
	}

}