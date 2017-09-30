package com.mjkim.sprhib.strategy.service;

import java.util.List;

import com.mjkim.sprhib.strategy.model.Strategy;

public interface StrategyService {

	public void addStrategy(Strategy strategy);

	public Strategy getStrategy(int id);

	public void updateStrategy(Strategy strategy);

	public void deleteStrategy(int id);

	public List<Strategy> getStrategies();

}
