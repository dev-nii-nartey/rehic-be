package com.rehic.members;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface MemberRepository extends CrudRepository<Member, String> {
    Optional<Member> findByEmailAddress(String emailAddress);

    Page<Member> findAllByIsDeletedFalse(Pageable pageable);

    Page<Member> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCaseAndIsDeletedFalse(String firstName, String lastName, Pageable pageable);

}
