package com.bookstore.jpa.dtos;

import java.util.Set;
import java.util.UUID;

public record BookRecordDtos(String title,
                             UUID publisherId,
                             Set<UUID>authorsIds,
                             String reviewComment) {
}
