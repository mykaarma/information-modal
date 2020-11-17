/**
 * Information Modal
 * Copyright (C) 2020 myKaarma.
 * opensource@mykaarma.com
 * <p>
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * <p>
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.mykaarma.oss.information.modal.api.jpa.model;

import com.mykaarma.oss.information.modal.model.enums.ModalContentType;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

/**
 *  DB Table containing Information about a single Modal
 */
@Data
@Entity
@Table(name = "ModalInfo")
public class ModalInfo {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    private Long id;

    @Version
    @Column(name = "Version", nullable = false)
    private Long version;

    @NotNull
    @Size(max = 255)
    @Column(name = "IdentifierTag", nullable = false, unique = true)
    private String identifierTag;

    @Size(max = 255)
    @Column(name = "AltText")
    private String altText;

    @Column(name = "ModalHeader")
    private String modalHeader;

    // 8K Resolution
    @Max(4320)
    @Min(10)
    @Positive
    @Column(name = "ModalHeightInPx", nullable = false)
    private Integer modalHeightInPx;

    // 8K Resolution
    @Max(7680)
    @Min(10)
    @Positive
    @Column(name = "ModalWidthInPx", nullable = false)
    private Integer modalWidthInPx;

    @NotNull
    @Column(name = "UUID", nullable = false, unique = true)
    private String uuid;

    @NotNull
    @Column(name = "ModalContentType", nullable = false)
    @Enumerated(EnumType.STRING)
    private ModalContentType modalContentType;

    @NotNull
    @Size(min = 1, max = 2048)
    @Column(name = "ModalContent", nullable = false)
    private String modalContent;
}
