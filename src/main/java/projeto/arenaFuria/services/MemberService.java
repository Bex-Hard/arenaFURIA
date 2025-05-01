package projeto.arenaFuria.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import projeto.arenaFuria.dto.register.MemberRegisterDTO;
import projeto.arenaFuria.dto.register.MemberRegisterResponseDTO;
import projeto.arenaFuria.dto.register.ResultResponseDTO;
import projeto.arenaFuria.entity.enums.RoomRoles;
import projeto.arenaFuria.entity.model.Member;
import projeto.arenaFuria.mapper.MemberMapper;
import projeto.arenaFuria.repository.MemberRepository;
import projeto.arenaFuria.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;
    private final UserRepository userRepository;

    public ResultResponseDTO createMember(MemberRegisterDTO dto) {
        var member = memberMapper.registerToEntity(dto);
        memberRepository.save(member);
        return new ResultResponseDTO(true, "Member created successfully.");
    }

    public MemberRegisterResponseDTO getMemberResponse(Member member) {
        return memberMapper.entityToResponse(member);
    }

    public Member getMemberById(String id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Member not found"));
    }
}
