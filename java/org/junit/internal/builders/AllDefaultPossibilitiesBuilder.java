package org.junit.internal.builders;

import java.util.*;

import org.junit.runner.*;
import org.junit.runners.model.*;

import br.ufsc.ine.leb.projetos.estoria.*;

public class AllDefaultPossibilitiesBuilder extends RunnerBuilder {

	private final boolean canUseSuiteMethod;

	public AllDefaultPossibilitiesBuilder(boolean canUseSuiteMethod) {
		this.canUseSuiteMethod = canUseSuiteMethod;
	}

	@Override
	public Runner runnerForClass(Class<?> testClass) throws Throwable {
		List<RunnerBuilder> builders = Arrays.asList(
				ignoredBuilder(),
				annotatedBuilder(),
				suiteMethodBuilder(),
				junit3Builder(),
				estoriaBuilder(),
				junit4Builder());
		for (RunnerBuilder each : builders) {
			Runner runner = each.safeRunnerForClass(testClass);
			if (runner != null) {
				return runner;
			}
		}
		return null;
	}

	protected ConstrutorDoEstoria estoriaBuilder() {
		return new ConstrutorDoEstoria();
	}

	protected JUnit4Builder junit4Builder() {
		return new JUnit4Builder();
	}

	protected JUnit3Builder junit3Builder() {
		return new JUnit3Builder();
	}

	protected AnnotatedBuilder annotatedBuilder() {
		return new AnnotatedBuilder(this);
	}

	protected IgnoredBuilder ignoredBuilder() {
		return new IgnoredBuilder();
	}

	protected RunnerBuilder suiteMethodBuilder() {
		if (canUseSuiteMethod) {
			return new SuiteMethodBuilder();
		}
		return new NullBuilder();
	}

}
