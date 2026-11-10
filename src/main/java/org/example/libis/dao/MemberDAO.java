package org.example.libis.dao;

import org.example.libis.model.Book;
import org.example.libis.model.Member;

import java.util.List;

public interface MemberDAO {
    List<Member> findAll();

    Member findById(int id);

    Member searchByUsername(String keyword);

    Member searchByMemberNumber(String keyword);

    Member searchByName(String keyword);

    Member searchByEmail(String keyword);

    void create(Member member);

    void update(Member member);

    void delete(int id);
}
