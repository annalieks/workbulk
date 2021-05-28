package com.univ.workbulk.column;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ColumnRepository extends JpaRepository<BoardColumn, UUID> {
}
