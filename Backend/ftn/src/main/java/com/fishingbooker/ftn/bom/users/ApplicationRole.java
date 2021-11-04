package com.fishingbooker.ftn.bom.users;

public enum ApplicationRole {

    REGISTERED_CLIENT,
    COTTAGE_OWNER,
    BOAT_OWNER,
    FISHING_INSTRUCTOR,
    ADMINISTRATOR;

    public static String toString(ApplicationRole role) {
        switch (role) {
            case REGISTERED_CLIENT:
                return "Registered Client";
            case COTTAGE_OWNER:
                return "Cottage Owner";
            case BOAT_OWNER:
                return "Boat Owner";
            case FISHING_INSTRUCTOR:
                return "Fishing Instructor";
            case ADMINISTRATOR:
                return "Administrator";
            default:
                return "Unknown role";
        }
    }

    public static ApplicationRole getRoleFromString(String role) {
        switch (role) {
            case "Registered Client":
                return ApplicationRole.REGISTERED_CLIENT;
            case "Boat Owner":
                return ApplicationRole.BOAT_OWNER;
            case "Cottage Owner":
                return ApplicationRole.COTTAGE_OWNER;
            case "Fishing Instructor":
                return ApplicationRole.FISHING_INSTRUCTOR;
            default:
                return ApplicationRole.ADMINISTRATOR;
        }
    }

}
