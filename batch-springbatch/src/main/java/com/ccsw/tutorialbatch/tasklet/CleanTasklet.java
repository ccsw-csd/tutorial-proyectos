package com.ccsw.tutorialbatch.tasklet;


import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.UnexpectedJobExecutionException;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.Resource;

import java.io.File;

public class CleanTasklet implements Tasklet, InitializingBean {

    private Resource directory;

    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        File dir = directory.getFile();

        File[] files = dir.listFiles();

		for (File file : files) {
			boolean deleted = file.delete();
			if (!deleted) {
				throw new UnexpectedJobExecutionException("Could not delete file " + file.getPath());
			}
		}
        return RepeatStatus.FINISHED;
    }

    public void setDirectoryResource(Resource directory) {

        this.directory = directory;
    }

    public void afterPropertiesSet() throws Exception {

		if (directory == null) {
			throw new UnexpectedJobExecutionException("Directory must be set");
		}
    }
}