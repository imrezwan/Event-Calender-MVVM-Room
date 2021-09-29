package com.rezwan2525.event_calender_mvvm_room.services.networks;

import java.util.concurrent.Executor;

public class FireAuthExecutor implements Executor {
    @Override
    public void execute(Runnable command) {
        command.run();
    }
}
