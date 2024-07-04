package fr.sncf.d2d.colitrack.controllers;

public record TimeDto(
        long time,
        String unit,
        String timeZone
) {}
