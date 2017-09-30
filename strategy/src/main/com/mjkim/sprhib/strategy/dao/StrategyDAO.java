package com.mjkim.sprhib.strategy.dao;

import java.util.List;

import com.mjkim.sprhib.strategy.model.Strategy;

public interface StrategyDAO {

	public void addStrategy(Strategy strategy);

	public Strategy getStrategy(int id);

	public void updateStrategy(Strategy strategy);

	public void deleteStrategy(int id);

	public List<Strategy> getStrategies();

}