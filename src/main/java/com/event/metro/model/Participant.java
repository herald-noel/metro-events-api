package com.event.metro.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Participant {
    String username;
    /*
     * 0 - For Approval
     * 1 - Approve
     * 2 - Decline
     */
    int status;
}
