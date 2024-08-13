package pre

// RedisKeyEnum 定义了 Redis 键的常量
const (
	LoginTokens         = "login-tokens:"
	Auth                = "auth:"
	Limit               = "limit:"
	LimitUserLogin      = "limit-user-login:"
	Course              = "course:"
	Schedule            = "schedule:"
	TeacherSign         = "teacherSign:"
	TeacherSignSport    = "teacherSign-sport:"
	Info                = "info:"
	Score               = "score:"
	GradeScore          = "grade-score:"
	Banner              = "banner:"
	UserIcons           = "user-icons:"
	Arrangement         = "arrangement:"
	MakeupArrangement   = "makeup-arrangement:"
	UserSchedule        = "user-schedule:"
	SchoolSystemsStatus = "schoolSystemsStatus:"
	LostFoundImg        = "lost-found-img:"
	LostFoundArticle    = "lost-found-article:"
	LostFoundComment    = "lost-found-comment:"
	WechatStep          = "wechat-step:"
	AccessToken         = "access_token:"

	// 以下是社区功能的redis-key
	CommunityComment     = "community_comment:"
	CommunityUpvote      = "community_upvote:"
	CommunityUserInfo    = "community_user_info:"
	CommunityUserSetting = "community_user_setting:"

	UserInfoTel   = "userinfo:tel:"
	UserInfoEmail = "userinfo:email:"
	UserInfoId    = "userinfo:id:"

	VerifyEmail = "verify:email:"

	LimitEmail = "limit:email:"

	DisplayPlantsAll    = "display:plants:all:"
	DisplayPlantsHas    = "display:plants:has:"
	DisplayPlantsNotHas = "display:plants:not_has:"
)
