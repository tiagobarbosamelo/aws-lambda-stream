package com.hp.lambda.kinesis; /**
 * (C) Copyright 2013-2016 HP Development Company, L.P.
 * Confidential computer software. Valid license from HP required for possession, use or copying.
 * Consistent with FAR 12.211 and 12.212, Commercial Computer Software,
 * Computer Software Documentation, and Technical Data for Commercial Items are licensed
 * to the U.S. Government under vendor's standard commercial license.
 */

import java.util.List;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.KinesisEvent;
import com.amazonaws.services.lambda.runtime.events.KinesisEvent.KinesisEventRecord;

public class LambdaKinesis implements RequestHandler<KinesisEvent, Object> {

    public String handleRequest(KinesisEvent input, Context context) {

        List<KinesisEventRecord> records = input.getRecords();

        StringBuilder sb = new StringBuilder();
        String separator = ",";

        for (KinesisEventRecord record : records) {

            sb.append(record.getAwsRegion())
                    .append(separator)
                    .append(record.getEventName())
                    .append(separator)
                    .append(new String(record.getKinesis().getData().array()));
        }

        context.getLogger().log("Input: " + sb.toString());

        return "Kinesis Stream event";
    }
}
