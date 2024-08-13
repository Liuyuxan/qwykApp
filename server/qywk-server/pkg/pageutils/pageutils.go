package pageutils

type PageUtils[T any] struct {
	PageNum  int
	PageSize int
	Src      []T
}

func NewPageUtils[T any](src []T, pageNum, pageSize int) *PageUtils[T] {
	return &PageUtils[T]{
		Src:      src,
		PageNum:  pageNum,
		PageSize: pageSize,
	}
}

func (p *PageUtils[T]) GetRecords() []T {
	total := len(p.Src)
	startIndex := (p.PageNum - 1) * p.PageSize
	endIndex := startIndex + p.PageSize
	if startIndex > total {
		return nil
	}
	if endIndex > total {
		endIndex = total
	}
	return p.Src[startIndex:endIndex]
}

func (p *PageUtils[T]) GetTotal() int {
	return len(p.Src)
}

func (p *PageUtils[T]) SetPageNum(pageNum int) {
	p.PageNum = pageNum
}

func (p *PageUtils[T]) SetPageSize(pageSize int) {
	p.PageSize = pageSize
}
